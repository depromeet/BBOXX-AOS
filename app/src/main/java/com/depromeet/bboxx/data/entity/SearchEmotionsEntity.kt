package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class SearchEmotionsEntity(
    @SerializedName("emotionDiary")
    val emotionDiary: List<EmotionDiaryEntity>,
    @SerializedName("emotionStatuses")
    val emotionStatuses: List<EmotionStatusEntity>
)
