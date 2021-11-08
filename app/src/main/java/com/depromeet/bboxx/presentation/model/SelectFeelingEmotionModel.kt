package com.depromeet.bboxx.presentation.model

data class SelectFeelingEmotionModel (
    val emotionUrl: String,
    val drawableid: Int,
    val id: Int,
    val text: String,
    var isSelected: Boolean = false
)