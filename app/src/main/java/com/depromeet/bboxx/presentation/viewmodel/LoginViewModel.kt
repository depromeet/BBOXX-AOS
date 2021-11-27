package com.depromeet.bboxx.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.domain.usecases.auth.AuthSignUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import com.depromeet.bboxx.presentation.model.TokenModel
import com.depromeet.bboxx.presentation.ui.result.Result
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

    val token = MutableLiveData<String>()
    fun signIn(auth: String, providerType: String){
        disposable+=
            authSignUseCase.signIn(auth, providerType)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        token.value = it.token
                    },
                    onError = {
                        loginEvent.value = Result.Error(it)
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