package com.depromeet.bboxx.data.dto

import com.depromeet.bboxx.data.entity.NotificationTokenEntity
import com.google.gson.annotations.SerializedName

data class NotificationTokenDto(
    @SerializedName("data")
    val data: NotificationTokenEntity
): BaseDto ()
