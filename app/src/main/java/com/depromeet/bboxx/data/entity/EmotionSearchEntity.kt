package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class EmotionSearchEntity(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("emotionStatusList")
    val emotionStatuses: List<EmotionStatusEntity>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("memberId")
    val memberId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updateAt: String
)
