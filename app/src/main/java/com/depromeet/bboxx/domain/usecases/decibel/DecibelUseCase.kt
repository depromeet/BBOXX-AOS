package com.depromeet.bboxx.domain.usecases.decibel

import com.depromeet.bboxx.domain.repository.decibel.DecibelRepository
import javax.inject.Inject

class DecibelUseCase @Inject constructor(
    private val decibelRepository: DecibelRepository
) {
    fun registerDecibel(decibel: Int, memberId: Int) =
        decibelRepository.registerDecibel(decibel, memberId)

    fun requestDecibelInfo(decibelId: Int) =
        decibelRepository.requestDecibelInfo(decibelId)
}