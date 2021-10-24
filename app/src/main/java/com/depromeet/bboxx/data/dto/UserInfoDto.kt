package com.depromeet.bboxx.data.dto

import com.depromeet.bboxx.data.entity.UserInfoEntity
import com.google.gson.annotations.SerializedName

data class UserInfoDto(
    @SerializedName("data") val data: UserInfoEntity
) : BaseDto()

