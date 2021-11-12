package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.depromeet.bboxx.domain.model.ImprovementDiaries
import javax.inject.Inject

class ImprovementDiariesEntityMapper @Inject constructor() {
    fun trans(target: List<ImprovementDiariesEntity>): List<ImprovementDiaries> = with(target) {
        return map { improve ->
            val createDate = improve.createdAt.substring(0, improve.createdAt.indexOf("."))
            ImprovementDiaries(
                improve.content,
                createDate,
                improve.emotionDiaryId,
                improve.id,
                improve.memberId,
                improve.tags,
                improve.title,
                improve.updatedAt
            )
        }
    }
}