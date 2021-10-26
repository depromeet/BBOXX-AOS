package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.DecibelEntity
import com.depromeet.bboxx.domain.model.DecibelInfo
import javax.inject.Inject

class DecibelEntityMapper @Inject constructor() {
    fun trans(target: DecibelEntity): DecibelInfo = with(target) {
        return DecibelInfo(decibel)
    }
}