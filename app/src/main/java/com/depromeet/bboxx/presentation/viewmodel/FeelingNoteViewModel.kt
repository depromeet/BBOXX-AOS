package com.depromeet.bboxx.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.domain.usecases.emotion.EmotionUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import com.depromeet.bboxx.presentation.mapper.FeelingEmotionMapper
import com.depromeet.bboxx.presentation.model.FeelingEmotionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class FeelingNoteViewModel @Inject constructor(
    private val emotionUseCase: EmotionUseCase,
    private val feelingEmotionMapper: FeelingEmotionMapper
): BaseViewModel() {


    private val _feelingEmotionList = MutableLiveData<List<FeelingEmotionModel>>()
    val feelingEmotionList: LiveData<List<FeelingEmotionModel>>
        get() = _feelingEmotionList

    fun getFeeling(){
        disposable+=
            emotionUseCase.requestEmotionStatus()
                .map(feelingEmotionMapper::trans)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        _feelingEmotionList.value = it
                    },
                    onError = {
                    }
                )
    }

    fun writeFeeling(categoryId: Int, content: String, emotionStatuses: List<Int>, memberId: Int, title: String){
        disposable+=
            emotionUseCase.registerEmotion(
                categoryId, content, emotionStatuses, memberId, title
            )
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {

                    },
                    onError = {
                    }
                )
    }

    fun searchFeelings(memberId: Int){
        disposable+=
            emotionUseCase.searchEmotion(memberId)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {

                    },
                    onError = {
                    }
                )
    }
}