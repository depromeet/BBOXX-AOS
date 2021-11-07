package com.depromeet.bboxx.data.repository.improvement

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.depromeet.bboxx.domain.model.ImprovementTags
import io.reactivex.rxjava3.core.Single

interface ImprovementDataSource {
    fun getImproveDiaries(memberId: Int, month: Int, year: Int): Single<List<ImprovementDiariesEntity>>

    fun writeImproveDiaries(
        content: String,
        emotionDiaryId: Int,
        memberId: Int,
        emotionTags: List<String>,
        title: String
    ): Single<EmptyDto>
}