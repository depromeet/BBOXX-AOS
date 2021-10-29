package com.depromeet.bboxx.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.constants.Constants.C_MOVE_PERSON_INFO
import com.depromeet.bboxx.constants.Constants.C_MOVE_TEAM
import com.depromeet.bboxx.constants.Constants.C_MOVE_TERMS_OF_USE
import com.depromeet.bboxx.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(): BaseViewModel() {

    private val _movePage = MutableLiveData<String>()
    val movePage : LiveData<String>
        get() = _movePage

    var nickname = MutableLiveData<String>()
    var version = MutableLiveData<String>()
    //  상태값 바뀌었을때 어떻게 행위할지 확인 필요
    var isPushStatus = MutableLiveData<Boolean>(false)

    fun moveTeamInfo(){
        _movePage.value = C_MOVE_TEAM
    }

    fun moveTermOfUse(){
        _movePage.value = C_MOVE_TERMS_OF_USE
    }

    fun movePersonInfo(){
        _movePage.value = C_MOVE_PERSON_INFO
    }

    //  구현 예정
    fun updateNickname(){

    }
}