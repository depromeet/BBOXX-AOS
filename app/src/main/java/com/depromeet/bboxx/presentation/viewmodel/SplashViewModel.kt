package com.depromeet.bboxx.presentation.viewmodel

import com.depromeet.bboxx.domain.usecases.auth.AuthSignUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.event.MoveToEvent
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import com.depromeet.bboxx.presentation.ui.AppContext
import com.depromeet.bboxx.presentation.utils.SingleLiveEvent
import com.depromeet.bboxx.util.SharedPreferenceUtil
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataBooleanSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataStringSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.setFirstRunSharedFix
import com.depromeet.bboxx.util.constants.SharedConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authSignUseCase: AuthSignUseCase
): BaseViewModel() {

    val naviToActivity = SingleLiveEvent<MoveToEvent>()
    var token: String = ""
    var fcmData: String = ""

    private fun checkFirstRun(): Boolean{
        var isFistRun = false
        AppContext.applicationContext()?.let{ context ->
            initSharedPreference(context, SharedConstants.C_FIRST_INSTALL_SHRED)
            isFistRun = getDataBooleanSharedPreference(SharedConstants.C_FIRST_INSTALL_KEY)!!

            if(isFistRun){
                setFirstRunSharedFix(SharedConstants.C_FIRST_INSTALL_KEY)
            }
        }
        return isFistRun
    }

    fun init(){
        AppContext.applicationContext()?.let{ context ->
            initSharedPreference(context, SharedConstants.C_JWT_SHRED)
            token = getDataStringSharedPreference(SharedConstants.C_JWT_KEY).toString()
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
                        if(checkFirstRun()){
                            naviToActivity.value = MoveToEvent.ONBOARD
                        }
                        else{
                            //  Token 상태 여부 체크
                            jwtValidStatusCheck()
                        }
                    },
                    onError = {

                    }
                )
    }

    private fun jwtValidStatusCheck(){
        disposable +=
            authSignUseCase.validToken(token)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        if(it.valid){
                            fcmMsgValueCheck()
                        }
                        else{
                            naviToActivity.value = MoveToEvent.LOGIN
                        }
                    },
                    onError = {
                        //   추후 수정 필요
                        naviToActivity.value = MoveToEvent.LOGIN
                    }
                )
    }

    /**
     *  알림 왔을때 메인이 실행되는지 여부 판단목적
     */
    private fun fcmMsgValueCheck(){
        AppContext.applicationContext()?.let { context ->
            initSharedPreference(
                context,
                SharedConstants.C_FCM_MSG_SHARED
            )
            val msg = getDataStringSharedPreference(SharedConstants.C_FCM_MSG_KEY)
            if(msg?.isNotBlank() == true){
                SharedPreferenceUtil.delSharedPreference()
                fcmData = msg
            }

            naviToActivity.value = MoveToEvent.MAIN
        }
    }
}