package com.depromeet.bboxx.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.presentation.base.BaseViewModel

class OnboardViewModel: BaseViewModel() {

    private val _onboardResult = MutableLiveData<String>()
    val onboardResult: LiveData<String>
        get() = _onboardResult

    val onboardNextEvent = MutableLiveData<Unit>()

    fun setOnboardResult(result: String) {
        _onboardResult.value = result
    }

    fun onNextStepEvent(){
        onboardNextEvent.value = Unit
    }
}