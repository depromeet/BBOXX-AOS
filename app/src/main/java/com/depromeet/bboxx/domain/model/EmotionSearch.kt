package com.depromeet.bboxx.domain.model

data class EmotionSearch(
    val emotionDiary: EmotionDiary,
    val emotionStatuses: List<EmotionStatus>
)
