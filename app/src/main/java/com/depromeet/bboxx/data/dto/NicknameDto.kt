package com.depromeet.bboxx.data.dto

import com.depromeet.bboxx.data.entity.NicknameEntity
import com.google.gson.annotations.SerializedName

data class NicknameDto(@SerializedName("data") val data: NicknameEntity) : BaseDto()
