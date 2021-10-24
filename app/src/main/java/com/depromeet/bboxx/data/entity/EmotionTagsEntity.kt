package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class EmotionTagsEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("improvementDiaryId")
    val improvementDiaryId: Int,
    @SerializedName("tag")
    val tag: String
)
