package com.depromeet.bboxx.domain.model

data class UserInfo (
    val id: Int,
    val nickname: String,
    val socialProviderType: String,
    val state: String
)