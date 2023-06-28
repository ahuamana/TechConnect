package com.ahuaman.client.data.firebase.repository

import com.ahuaman.client.data.firebase.remote.FirebaseRemoteDataSource
import com.techconnect.client.domain.UserReponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FirebaseRepository {
    suspend fun searchUserByEmail(email:String): Flow<UserReponse>
    suspend fun setUser(userData: UserReponse): Flow<Boolean>
}

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseRemoteDataSourceImpl: FirebaseRemoteDataSource
):FirebaseRepository {

    override suspend fun searchUserByEmail(email: String): Flow<UserReponse> {
        return firebaseRemoteDataSourceImpl.searchUserByEmail(email)
    }

    override suspend fun setUser(userData:UserReponse): Flow<Boolean> {
        return firebaseRemoteDataSourceImpl.setUser(userData)
    }
}