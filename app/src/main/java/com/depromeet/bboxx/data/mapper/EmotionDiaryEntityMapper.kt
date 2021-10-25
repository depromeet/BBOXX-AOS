package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.EmotionDiaryEntity
import com.depromeet.bboxx.domain.model.EmotionDiary
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class EmotionDiaryEntityMapper @Inject constructor(
    private val improvementDiariesEntityMapper: ImprovementDiariesEntityMapper
) {
    fun trans(target: EmotionDiaryEntity): EmotionDiary = with(target) {
        return EmotionDiary(
            categoryId,
            content,
            createdAt,
            emotionStatuses,
            id,
            Observable.fromIterable(improvementDiaries)
                .map { improvementDiariesEntityMapper.trans(it) }
                .toList()
                .blockingGet(),
            isNotiSent,
            memberId,
            title,
            updateAt
        )
    }
}