package com.depromeet.bboxx.presentation.model

data class NotificationModel(
    val createAt: String,
    val emotionDiaryId: Int,
    val id: Int,
    val message: String,
    val receiverId: Int,
    val state: String,
    val title: String,
    val updateAt: String
)
