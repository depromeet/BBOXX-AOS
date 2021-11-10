package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class NotificationsEntity(
    @SerializedName("createdAt")
    val createAt: String,
    @SerializedName("emotionDiaryId")
    val emotionDiaryId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("receiverId")
    val receiverId: Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updateAt: String
)
