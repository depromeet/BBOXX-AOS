package com.depromeet.bboxx.domain.model

data class EmotionDiary(
    val categoryId: Int,
    val content: String,
    val createdAt: String,
    val emotionStatuses: String,
    val id: Int,
    val improvementDiaries: List<ImprovementDiaries>,
    val isNotiSent: Boolean,
    val memberId: Int,
    val title: String,
    val updateAt: String
)
