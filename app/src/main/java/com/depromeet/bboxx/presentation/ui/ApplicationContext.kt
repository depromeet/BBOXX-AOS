package com.depromeet.bboxx.presentation.ui

import android.app.Application
import android.content.Context

class ApplicationContext : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: ApplicationContext? = null
        fun movieApplicationContext(): Context? {
            return instance?.applicationContext
        }
    }
}