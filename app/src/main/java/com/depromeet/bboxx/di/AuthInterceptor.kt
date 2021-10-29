package com.depromeet.bboxx.di

import com.depromeet.bboxx.presentation.ui.AppContext
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataStringSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants.C_JWT_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_JWT_SHRED
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Shared token 필요
        var token = ""
        AppContext.applicationContext()?.let{ context ->
            initSharedPreference(context, C_JWT_SHRED)
            token = getDataStringSharedPreference(C_JWT_KEY).toString()
        }
        var req =
            chain.request().newBuilder().addHeader("Authorization", "Bearer $token" ?: "").build()
        return chain.proceed(req)
    }
}