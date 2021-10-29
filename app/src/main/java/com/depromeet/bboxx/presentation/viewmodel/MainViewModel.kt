package com.depromeet.bboxx.presentation.viewmodel

import com.depromeet.bboxx.domain.model.UserInfo
import com.depromeet.bboxx.domain.usecases.userinfo.UserInfoUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import com.depromeet.bboxx.presentation.ui.AppContext
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.setDataSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_SHRED
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase
): BaseViewModel() {

    init{
        disposable+=
            userInfoUseCase.getMyPageInfo()
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        if(it != null){
                            saveUserId(it)
                        }
                    },
                    onError = {
                    }
                )
    }

    private fun saveUserId(userInfo: UserInfo){
        AppContext.applicationContext()?.let{
            initSharedPreference(it, C_MEMBER_ID_SHRED)
            setDataSharedPreference(userInfo.id, C_MEMBER_ID_KEY)
        }
    }
}
