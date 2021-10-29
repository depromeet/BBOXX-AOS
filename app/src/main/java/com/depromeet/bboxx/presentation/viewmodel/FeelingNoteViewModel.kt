package com.depromeet.bboxx.presentation.viewmodel

import com.depromeet.bboxx.domain.usecases.emotion.EmotionUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class FeelingNoteViewModel @Inject constructor(
    private val emotionUseCase: EmotionUseCase
): BaseViewModel() {

    fun writeFeeling(categoryId: Int, content: String, emotionStatuses: String, memberId: Int, title: String){
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
}