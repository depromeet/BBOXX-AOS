package com.depromeet.bboxx.domain.model

data class FCMMessage (
    val fcmId: Int =0,
    val message: String,
    val title: String,
    val deepUrl: String,
    val date: String,
    val isChecked: String)