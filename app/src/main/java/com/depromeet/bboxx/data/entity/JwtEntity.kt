package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class JwtEntity(
    @SerializedName("data")
    val data: Boolean
)