package com.ahuaman.client.usecases

import com.ahuaman.client.data.google.repo.IGoogleRepository
import javax.inject.Inject

class SignInUsecase @Inject constructor(
    private val signInRepository: IGoogleRepository
) {
    suspend operator fun invoke() = signInRepository.signIn()
}