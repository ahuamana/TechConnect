package com.ahuaman.client.data.google.di

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.ahuaman.client.data.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.techconnect.client.domain.SignInResult
import com.techconnect.client.domain.UserGoogle
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthUiClient (
    @ApplicationContext val context: Context
) {
    private val oneTapClient:SignInClient = Identity.getSignInClient(context)
    private val auth = Firebase.auth

    suspend fun signIn():Flow<IntentSender?> = flow{
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        }catch (e:Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
            null
        }
        emit(result?.pendingIntent?.intentSender)
    }

    suspend fun signInWithIntent(intent:Intent) : Flow<SignInResult> {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken,null)

        return flow {
            try {
                val user = auth.signInWithCredential(googleCredentials).await().user
                SignInResult(
                    data = user?.run {
                        UserGoogle(
                            email = uid,
                            username = displayName,
                            profilePictureUrl = photoUrl?.toString()
                        )
                    },
                    errorMessage = null
                )
            }catch (e:CancellationException) {
                if(e is CancellationException) throw e
                SignInResult(
                    data = null,
                    errorMessage = e.message
                )
            }
        }
    }

    suspend fun signOut(){
        try {
            auth.signOut()
            oneTapClient.signOut().await()
        }catch (e:CancellationException){
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): Flow<UserGoogle?>{
        val user = auth.currentUser
        return flow {
            emit(
                user?.run {
                    UserGoogle(
                        email = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                }
            )
        }
    }

    private fun buildSignInRequest():BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}

