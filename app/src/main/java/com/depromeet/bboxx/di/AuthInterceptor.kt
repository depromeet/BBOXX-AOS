package com.depromeet.bboxx.di

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Shared token 필요
        var req =
            chain.request().newBuilder().addHeader("Authorization", "App.prefs.token" ?: "").build()
        return chain.proceed(req)
    }
}