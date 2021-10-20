package com.depromeet.bboxx.data.dto

import com.google.gson.annotations.SerializedName

data class BaseDataDto(
    @SerializedName("code")
    var code: String = "",
    @SerializedName("message")
    var message: String = "",
    @SerializedName("data")
    var data : String? = null
)
