package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class RequestEmotionsEntity(
    @SerializedName("emotionStatuses")
    val emotionStatuses: List<EmotionStatusEntity>
)
