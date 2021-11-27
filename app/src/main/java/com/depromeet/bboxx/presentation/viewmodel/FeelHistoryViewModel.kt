package com.depromeet.bboxx.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.domain.usecases.emotion.EmotionUseCase
import com.depromeet.bboxx.domain.usecases.notice.NoticeUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import com.depromeet.bboxx.presentation.mapper.NotificationsMapper
import com.depromeet.bboxx.presentation.model.NotificationModel
import com.depromeet.bboxx.presentation.ui.result.Result
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

    val networkErrorEvent = MutableLiveData<Result<String>>()

    fun getNoticeList(memberId: Int) {
        disposable +=
            noticeUseCase.getNotificationInfoList(memberId)
                .map(notificationsMapper::trans)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        Log.d("_noticeList", it.toString())
                        _noticeList.value = it
                    },
                    onError = {
                        networkErrorEvent.value = Result.Error(it)
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