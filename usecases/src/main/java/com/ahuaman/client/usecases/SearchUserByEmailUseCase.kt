package com.ahuaman.client.usecases

import com.ahuaman.client.data.firebase.repository.FirebaseRepository
import com.techconnect.client.domain.UserDomain
import com.techconnect.client.domain.toUserDomain
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUserByEmailUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke(email:String) = firebaseRepository.searchUserByEmail(email).map { it.toUserDomain() }
}

data class SearhUserStates(
    val loading:Boolean = false,
    val userExist:Boolean = false,
    val userNotExist:Boolean = false,
    val error:Boolean = false,
    val errorMessage:String = "",
    val user: UserDomain? = null
)