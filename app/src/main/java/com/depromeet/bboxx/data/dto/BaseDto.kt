package com.depromeet.bboxx.data.dto

import com.google.gson.annotations.SerializedName

abstract class BaseDto {
    @SerializedName("code")
    val code: String = ""
    @SerializedName("message")
    val message: String = ""
}