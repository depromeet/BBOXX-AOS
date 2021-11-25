package com.depromeet.bboxx.presentation.extension

fun String?.isNotBlank(): Boolean {
    return !this.isNullOrBlank()
}
