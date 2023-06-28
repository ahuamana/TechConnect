package com.ahuaman.client.usecases

import com.ahuaman.client.data.google.repo.IGoogleRepository
import javax.inject.Inject

class GetUserSignedInfoUseCase @Inject constructor(
    private val googleRepository: IGoogleRepository
) {
    operator fun invoke() = googleRepository.getSignedInUser()
}
