package com.depromeet.bboxx.domain.model

data class EmotionDiary(
    val categoryId: Int,
    val content: String,
    val createdAt: String,
    val emotionStatuses: List<EmotionStatus>,
    val id: Int,
    val memberId: Int,
    val title: String,
    val updateAt: String
)
