package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class RequestEmotionsEntity(
    @SerializedName("emotions")
    val emotionStatuses: List<EmotionStatusEntity>
)
