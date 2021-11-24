package com.depromeet.bboxx.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depromeet.bboxx.domain.model.ImprovementDiaries
import com.depromeet.bboxx.domain.usecases.improve.ImproveUseCase
import com.depromeet.bboxx.domain.usecases.notice.NoticeUseCase
import com.depromeet.bboxx.presentation.base.BaseViewModel
import com.depromeet.bboxx.presentation.extension.onIOforMainThread
import com.depromeet.bboxx.presentation.model.GrowthNoteTagModel
import com.depromeet.bboxx.presentation.ui.AppContext
import com.depromeet.bboxx.util.SharedPreferenceUtil
import com.depromeet.bboxx.util.constants.SharedConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class GrowthNoteViewModel @Inject constructor(
    private val improveUseCase: ImproveUseCase,
    private val noticeUseCase: NoticeUseCase
) : BaseViewModel() {
    private val _growthList = MutableLiveData<List<ImprovementDiaries>>()
    val growthList: LiveData<List<ImprovementDiaries>>
        get() = _growthList

    fun getGrowthList(memberId: Int, month: Int, year: Int) {
        disposable +=
            improveUseCase.getImproveDiaries(memberId, month, year)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        Log.d("_growthList", it.toString())
                        _growthList.value = it
                    },
                    onError = {

                    }
                )
    }

    fun writeGrowth(
        content: String,
        emotionDiaryId: Int,
        tagList: List<GrowthNoteTagModel>,
        title: String
    ) {
        AppContext.applicationContext()?.let {
            SharedPreferenceUtil.initSharedPreference(it, SharedConstants.C_MEMBER_ID_SHRED)
        }

        //   여기 부분 리펙토링 필요해보임 from 중근
        val tag = mutableListOf<String>()
        tagList.forEach {
            tag.add(it.tag)
        }

        val memberId =
            SharedPreferenceUtil.getDataIntSharedPreference(SharedConstants.C_MEMBER_ID_KEY)

        disposable +=
            improveUseCase.writeImproveDiaries(content, emotionDiaryId, memberId!!, tag, title)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {
                        Log.d("IMPROVE", "SUCCESS")
                    },
                    onError = {
                    }
                )
    }


    fun testSendNotification(emotionDiaryId: Int, ownerId: Int) {
        disposable +=
            Observable.timer(7, TimeUnit.SECONDS)
                .onIOforMainThread()
                .subscribeBy(
                    onComplete = {
                        testSend(emotionDiaryId, ownerId)
                    },
                    onError = {

                    }
                )



    }

    fun testSend(emotionDiaryId: Int, ownerId: Int){
        disposable +=
            noticeUseCase.sendNotificationTest(emotionDiaryId, ownerId)
                .onIOforMainThread()
                .subscribeBy(
                    onSuccess = {

                    },
                    onError = {
                    }
                )
    }
}