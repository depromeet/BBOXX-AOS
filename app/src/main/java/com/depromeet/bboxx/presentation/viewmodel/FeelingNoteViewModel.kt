package com.depromeet.bboxx.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.domain.model.EmotionDiary
import com.depromeet.bboxx.domain.usecases.emotion.EmotionUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import com.depromeet.bboxx.presentation.mapper.FeelingEmotionMapper
import com.depromeet.bboxx.presentation.model.FeelingEmotionModel
import com.depromeet.bboxx.presentation.ui.result.Result
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

    private val _emotionDiary = MutableLiveData<EmotionDiary>()
    val emotionDiary: LiveData<EmotionDiary>
        get() = _emotionDiary

    val networkErrorEvent = MutableLiveData<Result<String>>()
    val searchNetworkErrorEvent = MutableLiveData<Result<String>>()

    fun getFeeling(){
        disposable+=
            emotionUseCase.requestEmotionStatus()
                .map(feelingEmotionMapper::trans)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        _feelingEmotionList.value = it
                        Log.d("FEEL", it.toString())
                    },
                    onError = {
                        networkErrorEvent.value = Result.Error(it)
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

    fun searchFeelings(emotionId: Int){
        disposable+=
            emotionUseCase.searchEmotion(emotionId)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        Log.d("searchFeelings", it.toString())
                        _emotionDiary.value = it
                    },
                    onError = {
                        searchNetworkErrorEvent.value = Result.Error(it)
                    }
                )
    }

    fun deleteFeelings(emotionId: Int){
        disposable+=
            emotionUseCase.deleteEmotion(emotionId)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {

                    },
                    onError = {

                    }
                )
    }
}