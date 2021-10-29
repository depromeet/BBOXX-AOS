package com.depromeet.bboxx.presentation.viewmodel

import com.depromeet.bboxx.domain.model.RegisterDecibel
import com.depromeet.bboxx.domain.usecases.decibel.DecibelUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class DecibelViewModel @Inject constructor(
    private val decibelUseCase: DecibelUseCase
): BaseViewModel() {

    lateinit var decibelModel: RegisterDecibel
    fun sendDecibelInfo(decibel: Int, memberId: Int){
        disposable+=
            decibelUseCase.registerDecibel(decibel, memberId)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        //  데시벨 ID 저장 필요
                        decibelModel = it
                    },
                    onError = {
                    }
                )
    }
}