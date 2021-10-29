package com.depromeet.bboxx.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.domain.model.Notifications
import com.depromeet.bboxx.domain.usecases.emotion.EmotionUseCase
import com.depromeet.bboxx.domain.usecases.notice.NoticeUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class FeelHistoryViewModel @Inject constructor(
    private val noticeUseCase: NoticeUseCase,
    private val emotionUseCase: EmotionUseCase
) : BaseViewModel() {

    private val _noticeList = MutableLiveData<List<Notifications>>()
    val noticeList: LiveData<List<Notifications>>
        get() = _noticeList


    fun getNoticeList() {
        disposable +=
            noticeUseCase.getNotificationInfoList()
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        _noticeList.value = it
                    },
                    onError = {

                    }

                )
    }

    fun deleteEmotion(emotionId: Int) {
        disposable +=
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