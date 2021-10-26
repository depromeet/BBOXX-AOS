package com.depromeet.bboxx.data.repository.improvement

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.depromeet.bboxx.domain.model.ImprovementTags
import io.reactivex.rxjava3.core.Single

interface ImprovementDataSource {
    fun getImproveDiaries(): Single<List<ImprovementDiariesEntity>>

    fun writeImproveDiaries(
        content: String,
        emotionDiaryId: Int,
        memberId: Int,
        emotionTags: List<ImprovementTags>,
        title: String
    ): Single<EmptyDto>
}