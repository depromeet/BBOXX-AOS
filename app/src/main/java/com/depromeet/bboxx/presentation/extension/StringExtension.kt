package com.depromeet.bboxx.presentation.extension

import org.json.JSONObject

fun String?.isNotBlank(): Boolean {
    return !this.isNullOrBlank()
}

fun String.toJson(): JSONObject? {
    return JSONObject(this)
}

fun String.getBearerAuthToken(): String {
    return "Bearer $this"
}
