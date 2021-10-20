package com.depromeet.bboxx.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.domain.model.Nickname
import com.depromeet.bboxx.domain.usecases.nickname.NicknameUseCase
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
    private val nicknameUseCase: NicknameUseCase
) : BaseViewModel() {

    //  2차 버전에서 리스트로 받을 것으로 List 로 표
    private val _nickNameList = MutableLiveData<Result<Nickname>>()
    val nickName : LiveData<Result<Nickname>>
        get() = _nickNameList

    val showNickname = MutableLiveData<String>()
    val likeEvent = SingleLiveEvent<Unit>()

    private fun getNickname(){
        disposable+=
            nicknameUseCase.getNickname()
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

    fun onLikeNickname(){
        likeEvent.value = Unit
    }
}