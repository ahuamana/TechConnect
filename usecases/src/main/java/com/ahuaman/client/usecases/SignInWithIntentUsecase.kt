package com.ahuaman.client.usecases

import android.content.Intent
import com.ahuaman.client.data.google.repo.IGoogleRepository
import javax.inject.Inject

class SignInWithIntentUsecase @Inject constructor(
    private val signInRepository: IGoogleRepository
) {
    suspend operator fun invoke(
        intent: Intent
    ) = signInRepository.signInWithIntent(intent)
}