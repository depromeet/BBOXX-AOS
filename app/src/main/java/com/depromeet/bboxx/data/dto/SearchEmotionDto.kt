package com.depromeet.bboxx.data.dto

import com.depromeet.bboxx.data.entity.EmotionSearchEntity
import com.google.gson.annotations.SerializedName

data class SearchEmotionDto(
    @SerializedName("data")
    val data: EmotionSearchEntity
): BaseDto()
