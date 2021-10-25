package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.depromeet.bboxx.domain.model.ImprovementDiaries
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ImprovementDiariesEntityMapper @Inject constructor(
    private val improvementTagsEntityMapper: ImprovementTagsEntityMapper
) {
    fun trans(target: ImprovementDiariesEntity): ImprovementDiaries = with(target) {
        return ImprovementDiaries(
            content,
            createdAt,
            emotionDiaryId,
            id,
            memberId,
            Observable.fromIterable(tags)
                .map { improvementTagsEntityMapper.trans(it) }
                .toList()
                .blockingGet(),
            title,
            updatedAt
        )
    }
}