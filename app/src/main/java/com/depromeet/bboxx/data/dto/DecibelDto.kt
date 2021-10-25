package com.depromeet.bboxx.data.dto

import com.depromeet.bboxx.data.entity.DecibelEntity
import com.google.gson.annotations.SerializedName

data class DecibelDto(
    @SerializedName("data")
    val data: DecibelEntity
)
