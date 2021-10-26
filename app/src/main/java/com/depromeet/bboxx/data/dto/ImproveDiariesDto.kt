package com.depromeet.bboxx.data.dto

import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.google.gson.annotations.SerializedName

data class ImproveDiariesDto(
    @SerializedName("data")
    val data: List<ImprovementDiariesEntity>
): BaseDto()
