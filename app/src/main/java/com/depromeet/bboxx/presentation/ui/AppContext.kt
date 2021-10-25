package com.depromeet.bboxx.presentation.ui

import android.app.Application
import android.content.Context
import com.depromeet.bboxx.R
import com.google.firebase.FirebaseApp
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppContext : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: AppContext? = null
        fun applicationContext(): Context? {
            return instance?.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        initKakaoTalk()
        initFirebase()

    }
    private fun initKakaoTalk() {
        KakaoSdk.init(this,getString(R.string.kakao_app_key))
    }

    private fun initFirebase(){
        FirebaseApp.initializeApp(this)
    }
}