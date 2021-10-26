package com.depromeet.bboxx.domain.model

data class NotificationToken(
    val id: Int,
    val ownerId: Int,
    val state: String
)
