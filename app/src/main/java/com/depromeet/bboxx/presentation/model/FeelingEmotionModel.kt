package com.depromeet.bboxx.presentation.model

data class FeelingEmotionModel(
    val emotionUrl: String,
    val id: Int,
    val text: String,
    var isSelected: Boolean = false
)
