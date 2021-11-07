package com.depromeet.bboxx.data.dto

import com.google.gson.annotations.SerializedName
import com.depromeet.bboxx.data.entity.TokenValidEntity

data class TokenValidDto(
    @SerializedName("data")
    val data: TokenValidEntity
): BaseDto()
