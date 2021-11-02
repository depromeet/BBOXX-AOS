package com.depromeet.bboxx.presentation.viewmodel

import android.util.Log
import com.depromeet.bboxx.domain.model.UserInfo
import com.depromeet.bboxx.domain.usecases.notice.NoticeUseCase
import com.depromeet.bboxx.domain.usecases.userinfo.UserInfoUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import com.depromeet.bboxx.presentation.ui.AppContext
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.setDataIntSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_SHRED
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val notificationUseCase: NoticeUseCase
) : BaseViewModel() {

    private var ownerId = -1
    private var token = ""

    //  OwnerID를 받고자 API 통신
    fun getOwnerId() {
        disposable +=
            userInfoUseCase.getMyPageInfo()
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        if (it != null) {
                            saveUserId(it)
                        }
                    },
                    onError = {
                    }
                )
    }

    private fun getFCMToken(): String {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            token = task.result.toString()

            // Log and toast
            Log.d("TAG", token)
        })

        return token
    }

    fun pushFCMToken() {
        disposable +=
            notificationUseCase.registerNotification(1, getFCMToken())
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {

                    },
                    onError = {
                    }
                )
    }

    // FCM Token 을 여기서 처리 해야됨
    private fun saveUserId(userInfo: UserInfo) {
        ownerId = userInfo.id
        AppContext.applicationContext()?.let {
            initSharedPreference(it, C_MEMBER_ID_SHRED)
            setDataIntSharedPreference(userInfo.id, C_MEMBER_ID_KEY)
        }

        getFCMToken()
    }
}
