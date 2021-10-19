package com.depromeet.bboxx.domain.enums

data class SnsVerifyEvent(
    val email: String,
    val mobileNumber: String,
    val socialUserId: String,
    val snsPlatformType: PlatformType,
    val accessToken: String
)
