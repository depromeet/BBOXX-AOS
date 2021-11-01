package com.depromeet.bboxx.presentation.model

data class GrowthNoteModel(
    val content: String,
    val emotionDiaryId: Int,
    val memberId: Int,
    val tags: List<GrowthNoteTagModel>,
    val title: String
)