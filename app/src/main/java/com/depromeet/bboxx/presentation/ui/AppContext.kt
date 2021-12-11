package com.depromeet.bboxx.presentation.ui

import android.app.Application
import android.content.Context
import com.depromeet.bboxx.R
import com.depromeet.bboxx.constants.Constants.REMOTE_CONFIG_CACHE_EXPIRATON
import com.depromeet.bboxx.presentation.extension.CrashHandler
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
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
        initCrashHandler()

    }
    private fun initKakaoTalk() {
        KakaoSdk.init(this,getString(R.string.kakao_app_key))
    }

    private fun initFirebase(){

        FirebaseApp.initializeApp(this)

        val firebaseRemoteConfigSetting = FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(1).build()
        val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        firebaseRemoteConfig.setConfigSettingsAsync(firebaseRemoteConfigSetting)
        firebaseRemoteConfig.fetch(REMOTE_CONFIG_CACHE_EXPIRATON.toLong()).addOnSuccessListener {
            firebaseRemoteConfig.activate()
        }

    }
    private fun initCrashHandler() {

        val defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler{_,_ ->
        }
        val firebaseExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(
            CrashHandler(
                this,
                defaultExceptionHandler,
                firebaseExceptionHandler
            )
        )
    }
}