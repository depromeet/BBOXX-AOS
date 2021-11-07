package com.depromeet.bboxx.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.domain.model.Nickname
import com.depromeet.bboxx.domain.usecases.auth.AuthSignUseCase
import com.depromeet.bboxx.domain.usecases.userinfo.UserInfoUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import com.depromeet.bboxx.presentation.ui.result.Result
import com.depromeet.bboxx.presentation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class NicknameViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val authSignUseCase: AuthSignUseCase
) : BaseViewModel() {

    //  2차 버전에서 리스트로 받을 것으로 List 로 표
    private val _nickNameList = MutableLiveData<Result<Nickname>>()
    val nickName : LiveData<Result<Nickname>>
        get() = _nickNameList

    val showNickname = MutableLiveData<String>()
    val accessToken = MutableLiveData<String>()
    val providerType = MutableLiveData<String>()
    val likeResult = SingleLiveEvent<String>()

    private fun getNickname(){
        disposable+=
            userInfoUseCase.getNickname()
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        _nickNameList.value = Result.Success(it)
                        showNickname.value = it.nickName
                    },
                    onError = {
                        _nickNameList.value = Result.Error(it)
                    }
                )
    }

    fun initNickName(){
        getNickname()
    }

    fun getAgainNickname(){
        getNickname()
    }

    fun setAccessToken(token: String){
        accessToken.value = token
    }

    fun setProviderType(type: String){
        providerType.value = type
    }

    fun onLikeNickname(){
        disposable+=
            authSignUseCase.signUp(accessToken.value!! ,showNickname.value!!, providerType.value!!)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = { token ->
                        likeResult.value = token.token
                    },
                    onError = {
                        likeResult.value = it.message
                    }
                )
    }
}