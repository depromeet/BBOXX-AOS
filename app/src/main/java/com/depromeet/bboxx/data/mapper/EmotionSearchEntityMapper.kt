package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.EmotionSearchEntity
import com.depromeet.bboxx.domain.model.EmotionSearch
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class EmotionSearchEntityMapper @Inject constructor(
    private val emotionDiaryEntityMapper: EmotionDiaryEntityMapper,
    private val emotionStatusEntityMapper: EmotionStatusEntityMapper
) {
    fun trans(target: EmotionSearchEntity): EmotionSearch = with(target){
        return EmotionSearch(
            emotionDiaryEntityMapper.trans(emotionDiary),
            Observable.fromIterable(emotionStatuses)
                .map { emotionStatusEntityMapper.trans(it) }
                .toList()
                .blockingGet()
        )
    }
}