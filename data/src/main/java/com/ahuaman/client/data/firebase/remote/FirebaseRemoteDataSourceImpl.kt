package com.ahuaman.client.data.firebase.remote

import com.ahuaman.client.data.firebase.di.UsersCollection
import com.google.firebase.firestore.CollectionReference
import com.techconnect.client.domain.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface FirebaseRemoteDataSource {
    fun searchUserByEmail(email:String): Flow<UserData>
    fun setUser(userData:UserData): Flow<Boolean>
}

class FirebaseRemoteDataSourceImpl @Inject constructor(
    @UsersCollection private val usersCollection: CollectionReference
):FirebaseRemoteDataSource {
    override fun searchUserByEmail(email: String):Flow<UserData>  = flow {
        val user = usersCollection
            .whereEqualTo("email", email)
            .limit(1)
            .get()
            .await()
            .toObjects(UserData::class.java)
            .firstOrNull()
        emit(user ?: UserData())
    }

    override fun setUser(userData:UserData): Flow<Boolean> {
        return flow {
            usersCollection
                .document(userData.email)
                .set(userData)
                .await()
            emit(true)
        }
    }
}