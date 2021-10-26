package com.depromeet.bboxx.domain.model

data class Notifications(
    val createAt: String,
    val emotionDiaryId: Int,
    val id: Int,
    val message: String,
    val receiverId: Int,
    val state: String,
    val title: String,
    val updateAt: String
)
