package com.depromeet.bboxx.data.dto

import com.depromeet.bboxx.data.entity.RequestEmotionsEntity
import com.google.gson.annotations.SerializedName

data class RequestEmotionsDto(
    @SerializedName("data")
    val data: RequestEmotionsEntity
): BaseDto()
