package com.depromeet.bboxx.presentation.viewmodel

import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.event.MoveToEvent
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import com.depromeet.bboxx.presentation.ui.AppContext
import com.depromeet.bboxx.presentation.utils.SingleLiveEvent
import com.depromeet.bboxx.util.SharedPreferenceUtil.delSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): BaseViewModel() {

    val naviToActivity = SingleLiveEvent<MoveToEvent>()

    fun init(){
        AppContext.applicationContext()?.let{ context ->
            initSharedPreference(context, SharedConstants.C_JWT_SHRED)
            delSharedPreference(SharedConstants.C_JWT_KEY).toString()
        }

        disposable +=
            Observable.timer(2, TimeUnit.SECONDS)
                .onIOforMainThread()
                .subscribeBy(
                    onComplete = {
                        naviToActivity.value = MoveToEvent.OPEN
                    },
                    onError = {

                    }
                )

    }

    fun nextStep(){
        disposable +=
            Observable.timer(2, TimeUnit.SECONDS)
                .onIOforMainThread()
                .subscribeBy(
                    onComplete = {
                        naviToActivity.value = MoveToEvent.ONBOARD
                    },
                    onError = {

                    }
                )
    }
}