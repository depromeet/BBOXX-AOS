package com.depromeet.bboxx.data.entity

import com.google.gson.annotations.SerializedName

data class NotificationTokenEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("ownerId")
    val ownerId: Int,
    @SerializedName("state")
    val state: String
)
