package com.ahuaman.client.usecases


import com.ahuaman.client.data.firebase.repository.FirebaseRepository

import com.techconnect.client.domain.UserDomain
import com.techconnect.client.domain.toUserDbFromDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetUserFirebaseUseCase  @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke(userData: UserDomain): Flow<Boolean> {
        return firebaseRepository.setUser(userData.toUserDbFromDomain())
    }
}