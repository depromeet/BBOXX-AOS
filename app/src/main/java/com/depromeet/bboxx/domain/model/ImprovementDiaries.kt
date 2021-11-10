package com.depromeet.bboxx.domain.model

data class ImprovementDiaries(
    val content: String,
    val createdAt: String,
    val emotionDiaryId: Int,
    val id: Int,
    val memberId: Int,
    val tags: List<String>,
    val title: String,
    val updatedAt: String
)
