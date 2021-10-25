package com.depromeet.bboxx.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.constants.Constants.C_SUCCESS
import com.depromeet.bboxx.domain.usecases.auth.AuthSignUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import com.depromeet.bboxx.presentation.model.TokenModel
import com.depromeet.bboxx.presentation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authSignUseCase: AuthSignUseCase
): BaseViewModel() {

    val loginEvent = MutableLiveData<Result<TokenModel>>()
    val snsLoginResult = SingleLiveEvent<String>()

    val snsLoginEvent = SingleLiveEvent<String>()

    fun signIn(auth: String, providerType: String){
        disposable+=
            authSignUseCase.signIn(auth, providerType)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        snsLoginResult.value = C_SUCCESS
                    },
                    onError = {
                    }
                )

    }

    fun onSnsLoginKakao(){
        snsLoginEvent.value = "kakao"
    }

    fun onSnsLoginGoogle(){
        snsLoginEvent.value = "google"
    }
}