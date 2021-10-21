package com.depromeet.bboxx.data.dto

import com.depromeet.bboxx.data.entity.TokenEntity
import com.google.gson.annotations.SerializedName

data class TokenDto(@SerializedName("data") val data: TokenEntity): BaseDto()
