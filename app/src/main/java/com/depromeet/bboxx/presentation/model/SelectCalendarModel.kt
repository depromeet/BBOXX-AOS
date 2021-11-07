package com.depromeet.bboxx.presentation.model

import android.support.annotation.ColorRes

data class SelectCalendarModel(
    val monthData: String,
    @ColorRes
    val color: Int,
    @ColorRes
    val textColor: Int,
    var isClicked: Boolean = false,
    val month: Int
)
