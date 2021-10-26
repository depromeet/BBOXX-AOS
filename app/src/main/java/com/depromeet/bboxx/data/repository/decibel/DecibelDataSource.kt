package com.depromeet.bboxx.data.repository.decibel

import com.depromeet.bboxx.data.entity.DecibelEntity
import com.depromeet.bboxx.data.entity.RegisterDecibelEntity
import io.reactivex.rxjava3.core.Single

interface DecibelDataSource {
    fun registerDecibel(decibel: Int, memberId: Int) : Single<RegisterDecibelEntity>

    fun requestDecibelInfo(decibelId: Int): Single<DecibelEntity>
}