package com.depromeet.bboxx.presentation.extension

import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashHandler(private val defaultExceptionHandler : Thread.UncaughtExceptionHandler,
                   private val firebaseExceptionHandler : Thread.UncaughtExceptionHandler) : Thread.UncaughtExceptionHandler {

    private val TAG = "UIStatus"
    private val C_TAG = "Crash"
    private val crashlytics = FirebaseCrashlytics.getInstance()


    override fun uncaughtException(t: Thread, e: Throwable) {
        crashlytics.setCustomKey(C_TAG, e.localizedMessage)
        defaultExceptionHandler.uncaughtException(t,e)
    }
}

