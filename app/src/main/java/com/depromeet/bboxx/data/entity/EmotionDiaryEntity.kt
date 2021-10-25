package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class EmotionDiaryEntity(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("emotionStatuses")
    val emotionStatuses: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("improvementDiaries")
    val improvementDiaries: List<ImprovementDiariesEntity>,
    @SerializedName("isNotiSent")
    val isNotiSent: Boolean,
    @SerializedName("memberId")
    val memberId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("updateAt")
    val updateAt: String
)
