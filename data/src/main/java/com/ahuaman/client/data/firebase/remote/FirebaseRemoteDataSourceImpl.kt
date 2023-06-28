package com.ahuaman.client.data.firebase.remote

import com.ahuaman.client.data.firebase.di.UsersCollection
import com.google.firebase.firestore.CollectionReference
import com.techconnect.client.domain.UserReponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface FirebaseRemoteDataSource {
    fun searchUserByEmail(email:String): Flow<UserReponse>
    fun setUser(userData:UserReponse): Flow<Boolean>
}

class FirebaseRemoteDataSourceImpl @Inject constructor(
    @UsersCollection private val usersCollection: CollectionReference
):FirebaseRemoteDataSource {
    override fun searchUserByEmail(email: String):Flow<UserReponse>  = flow {
        val user = usersCollection
            .whereEqualTo("email", email)
            .limit(1)
            .get()
            .await()
            .toObjects(UserReponse::class.java)
            .firstOrNull()
        emit(user ?: UserReponse())
    }

    override fun setUser(userData:UserReponse): Flow<Boolean> {
        return flow {
            usersCollection
                .document(userData.email)
                .set(userData)
                .await()
            emit(true)
        }
    }
}