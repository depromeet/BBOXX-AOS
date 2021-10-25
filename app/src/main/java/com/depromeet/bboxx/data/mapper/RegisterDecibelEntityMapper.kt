package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.RegisterDecibelEntity
import com.depromeet.bboxx.domain.model.RegisterDecibel
import javax.inject.Inject

class RegisterDecibelEntityMapper @Inject constructor() {
    fun trans(target: RegisterDecibelEntity) : RegisterDecibel = with(target){
        return RegisterDecibel(id)
    }
}