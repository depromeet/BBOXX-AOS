package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.RequestEmotionsEntity
import com.depromeet.bboxx.domain.model.RequestEmotions
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RequestEmotionEntityMapper @Inject constructor(
    private val emotionStatusEntityMapper: EmotionStatusEntityMapper
) {
    fun trans(target: RequestEmotionsEntity): RequestEmotions = with(target) {
        return RequestEmotions(
            Observable.fromIterable(emotionStatuses)
            .map { emotionStatusEntityMapper.trans(it) }
            .toList()
            .blockingGet())
    }
}