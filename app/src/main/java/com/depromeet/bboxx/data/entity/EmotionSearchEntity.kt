package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class EmotionSearchEntity(
    @SerializedName("emotionDiary")
    val emotionDiary: EmotionDiaryEntity,
    @SerializedName("emotionStatuses")
    val emotionStatuses: List<EmotionStatusEntity>
)
