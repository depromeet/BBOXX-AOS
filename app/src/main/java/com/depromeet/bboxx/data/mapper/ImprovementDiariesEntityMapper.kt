package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.depromeet.bboxx.domain.model.ImprovementDiaries
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ImprovementDiariesEntityMapper @Inject constructor(
    private val improvementTagsEntityMapper: ImprovementTagsEntityMapper
) {
    fun trans(target: List<ImprovementDiariesEntity>): List<ImprovementDiaries> = with(target) {
        return map { improve ->
            ImprovementDiaries(
                improve.content,
                improve.createdAt,
                improve.emotionDiaryId,
                improve.id,
                improve.memberId,
                Observable.fromIterable(improve.tags)
                    .map { improvementTagsEntityMapper.trans(it) }
                    .toList()
                    .blockingGet(),
                improve.title,
                improve.updatedAt
            )
        }
    }
}