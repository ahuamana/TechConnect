package com.ahuaman.client.data.google.repo

import android.content.Intent
import android.content.IntentSender
import com.ahuaman.client.data.google.di.GoogleAuthUiClient
import com.techconnect.client.domain.SignInResult
import com.techconnect.client.domain.UserGoogle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface IGoogleRepository {
    fun getSignedInUser(): Flow<UserGoogle?>

    suspend fun signInWithIntent(intent: Intent): Flow<SignInResult>

    suspend fun signIn(): Flow<IntentSender?>

}

class GoogleRepository @Inject constructor(
    private val googleAuthUiClient: GoogleAuthUiClient
) : IGoogleRepository {
    override fun getSignedInUser(): Flow<UserGoogle?> {
        return googleAuthUiClient.getSignedInUser()
    }

    override suspend fun signInWithIntent(intent: Intent): Flow<SignInResult> {
        return googleAuthUiClient.signInWithIntent(intent)
    }

    override suspend fun signIn(): Flow<IntentSender?> {
        return googleAuthUiClient.signIn()
    }
}