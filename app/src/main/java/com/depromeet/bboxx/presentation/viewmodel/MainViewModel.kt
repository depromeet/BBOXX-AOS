package com.depromeet.bboxx.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.domain.model.UserInfo
import com.depromeet.bboxx.domain.usecases.notice.NoticeUseCase
import com.depromeet.bboxx.domain.usecases.userinfo.UserInfoUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import com.depromeet.bboxx.presentation.ui.AppContext
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataIntSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataStringSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.setDataBooleanSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.setDataIntSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.setDataStringSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants.C_FCM_TOKEN_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_FCM_TOKEN_SHRED
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_SHRED
import com.depromeet.bboxx.util.constants.SharedConstants.C_NICKNAME_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_NICKNAME_SHRED
import com.depromeet.bboxx.util.constants.SharedConstants.C_PUSH_STATUS_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_PUSH_STATUS_SHRED
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

    private var _nickName = MutableLiveData<String>()
    val nickName : LiveData<String>
        get() = _nickName

    init {
        //  FCM Token ???????????? Token ??? ????????????.
        //  ?????? ?????? ?????? ??????...?????? (????????????... ??????!!)
        getFCMToken()

        //  OwnerId ????????????
        getOwnerId()
    }
    //  OwnerID??? ????????? API ??????
    fun getOwnerId() {
        disposable +=
            userInfoUseCase.getMyPageInfo()
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        if (it != null) {
                            _nickName.value = it.nickname
                            saveUserId(it)
                        }
                    },
                    onError = {
                    }
                )
    }

    private fun saveUserId(userInfo: UserInfo) {
        ownerId = userInfo.id
        AppContext.applicationContext()?.let {
            initSharedPreference(it, C_MEMBER_ID_SHRED)
            setDataIntSharedPreference(userInfo.id, C_MEMBER_ID_KEY)

            initSharedPreference(it, C_NICKNAME_SHRED)
            setDataStringSharedPreference(userInfo.nickname, C_NICKNAME_KEY)

            initSharedPreference(it, C_PUSH_STATUS_SHRED)
            setDataBooleanSharedPreference(true, C_PUSH_STATUS_KEY)
        }

        pushRegister()
    }


    /**
     *  ?????? ????????? ?????? ?????? ?????? ?????? from. ??????
     */
    private fun getFCMToken(): String {
        AppContext.applicationContext()?.let{
            initSharedPreference(it, C_FCM_TOKEN_SHRED)
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            token = task.result.toString()

            setDataStringSharedPreference(token, C_FCM_TOKEN_KEY)
            // Log and toast
            Log.d("TAG", token)
        })

        return token
    }

    /**
     *  ?????? ?????? ??????
     */
    fun pushRegister() {
        var memberId = -1
        var fcmToken = ""

        AppContext.applicationContext()?.let {
            initSharedPreference(it, C_MEMBER_ID_SHRED)
            memberId = getDataIntSharedPreference(C_MEMBER_ID_KEY)!!

            initSharedPreference(it, C_FCM_TOKEN_SHRED)
            fcmToken = getDataStringSharedPreference(C_FCM_TOKEN_KEY)!!
        }

        disposable +=
            notificationUseCase.registerNotification(memberId, fcmToken)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {

                    },
                    onError = {
                    }
                )
    }

    /**
     *  ?????? ?????? ??????
     */
    fun pushDeRegister(){
        var memberId = -1

        AppContext.applicationContext()?.let {
            initSharedPreference(it, C_MEMBER_ID_SHRED)
            memberId = getDataIntSharedPreference(C_MEMBER_ID_KEY)!!
        }

        disposable +=
            notificationUseCase.deRegisterNotification(memberId)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {

                    },
                    onError = {
                    }
                )
    }
}
