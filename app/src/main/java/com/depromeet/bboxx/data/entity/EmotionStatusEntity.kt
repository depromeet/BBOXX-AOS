package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class EmotionStatusEntity(
    @SerializedName("emotionUrl")
    val emotionUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("status")
    val status: String
)
