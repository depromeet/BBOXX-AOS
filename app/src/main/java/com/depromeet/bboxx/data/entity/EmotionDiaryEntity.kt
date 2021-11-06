package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class EmotionDiaryEntity(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("emotionStatusList")
    val emotionStatusList: List<EmotionStatusEntity>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("memberId")
    val memberId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("updateAt")
    val updateAt: String
)
