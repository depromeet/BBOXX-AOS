package com.depromeet.bboxx.domain.repository.decibel

import com.depromeet.bboxx.domain.model.DecibelInfo
import com.depromeet.bboxx.domain.model.RegisterDecibel
import io.reactivex.rxjava3.core.Single

interface DecibelRepository {
    fun registerDecibel(decibel: Int, memberId: Int): Single<RegisterDecibel>

    fun requestDecibelInfo(decibelId: Int): Single<DecibelInfo>
}