package com.ahuaman.client.data.firebase.repository

import com.ahuaman.client.data.firebase.remote.FirebaseRemoteDataSource
import com.techconnect.client.domain.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FirebaseRepository {
    suspend fun searchUserByEmail(email:String): Flow<UserData>
    suspend fun setUser(userData:UserData): Flow<Boolean>
}

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseRemoteDataSourceImpl: FirebaseRemoteDataSource
):FirebaseRepository {
    override suspend fun searchUserByEmail(email: String): Flow<UserData> {
        return firebaseRemoteDataSourceImpl.searchUserByEmail(email)
    }

    override suspend fun setUser(userData:UserData): Flow<Boolean> {
        return firebaseRemoteDataSourceImpl.setUser(userData)
    }
}