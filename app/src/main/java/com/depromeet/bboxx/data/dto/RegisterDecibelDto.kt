package com.depromeet.bboxx.data.dto

import com.depromeet.bboxx.data.entity.RegisterDecibelEntity
import com.google.gson.annotations.SerializedName

data class RegisterDecibelDto(
    @SerializedName("data")
    val data: RegisterDecibelEntity
): BaseDto()
