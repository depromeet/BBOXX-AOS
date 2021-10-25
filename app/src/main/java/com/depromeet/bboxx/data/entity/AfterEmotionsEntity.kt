package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class AfterEmotionsEntity(
    @SerializedName("emotionStatuses")
    val emotionStatuses: List<EmotionStatusEntity>
)
