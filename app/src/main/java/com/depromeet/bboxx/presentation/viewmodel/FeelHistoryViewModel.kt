package com.depromeet.bboxx.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.domain.usecases.emotion.EmotionUseCase
import com.depromeet.bboxx.domain.usecases.notice.NoticeUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import com.depromeet.bboxx.presentation.mapper.NotificationsMapper
import com.depromeet.bboxx.presentation.model.NotificationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class FeelHistoryViewModel @Inject constructor(
    private val noticeUseCase: NoticeUseCase,
    private val emotionUseCase: EmotionUseCase,
    private val notificationsMapper: NotificationsMapper
) : BaseViewModel() {

    private val _noticeList = MutableLiveData<List<NotificationModel>>()
    val noticeList: LiveData<List<NotificationModel>>
        get() = _noticeList

    fun setNotificationList(list: List<NotificationModel>){
        _noticeList.value = list
    }


    fun getNoticeList(memberId: Int) {
        disposable +=
            noticeUseCase.getNotificationInfoList(1)
                .map(notificationsMapper::trans)
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