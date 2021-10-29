package com.depromeet.bboxx.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.domain.model.ImprovementDiaries
import com.depromeet.bboxx.domain.usecases.improve.ImproveUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class GrowthNoteViewModel @Inject constructor(
    private val improveUseCase: ImproveUseCase
):BaseViewModel() {
    private val _growthList = MutableLiveData<List<ImprovementDiaries>>()
    val growthList : LiveData<List<ImprovementDiaries>>
        get() = _growthList

    fun getGrowthList(){
        disposable+=
            improveUseCase.getImproveDiaries()
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        it.also { _growthList.value = it }
                    },
                    onError = {
                    }
                )
    }
}