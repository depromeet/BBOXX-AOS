package com.depromeet.bboxx.data.dto

import com.depromeet.bboxx.data.entity.NotificationsEntity
import com.google.gson.annotations.SerializedName

data class NotificationsDto(
    @SerializedName("data")
    val data: NotificationsEntity
): BaseDto()
