package com.depromeet.bboxx.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.model.TokenModel
import com.depromeet.bboxx.presentation.utils.SingleLiveEvent

class LoginViewModel: BaseViewModel() {

    val loginEvent = MutableLiveData<Result<TokenModel>>()
    val snsLoginResult = SingleLiveEvent<String>()

    val snsLoginEvent = SingleLiveEvent<String>()

    private var fireBaseInstanceId: String? = ""
    private var publicKey: String = ""
    private var userUniqueKey : String = ""

    fun onSnsLoginKakao(){
        snsLoginEvent.value = "kakao"
    }

    fun onSnsLoginGoogle(){
        snsLoginEvent.value = "google"
    }
}