package com.depromeet.bboxx.data.dto

import com.depromeet.bboxx.data.entity.JwtEntity
import com.google.gson.annotations.SerializedName

data class JwtDto(
    @SerializedName("data") val data: JwtEntity
) : BaseDto()