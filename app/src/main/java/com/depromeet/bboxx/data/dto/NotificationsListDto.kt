package com.depromeet.bboxx.data.dto

import com.depromeet.bboxx.data.entity.NotificationsEntity
import com.google.gson.annotations.SerializedName

data class NotificationsListDto(
    @SerializedName("data")
    val data: List<NotificationsEntity>
): BaseDto()
