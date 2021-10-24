package com.depromeet.bboxx.data.dto

import com.depromeet.bboxx.data.entity.AfterEmotionsEntity
import com.google.gson.annotations.SerializedName

data class AfterGetEmotionsDto(
    @SerializedName("data")
    val data: AfterEmotionsEntity): BaseDto()
)
