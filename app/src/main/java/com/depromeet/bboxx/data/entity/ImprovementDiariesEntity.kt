package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class ImprovementDiariesEntity(
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("emotionDiaryId")
    val emotionDiaryId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("memberId")
    val memberId: Int,
    @SerializedName("tags")
    val tags: List<ImprovementTagsEntity>,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)
