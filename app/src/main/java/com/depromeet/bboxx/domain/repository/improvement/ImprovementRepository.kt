package com.depromeet.bboxx.domain.repository.improvement

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.domain.model.ImprovementDiaries
import io.reactivex.rxjava3.core.Single

interface ImprovementRepository {
    fun getImproveDiaries(memberId: Int, month: Int, year: Int): Single<List<ImprovementDiaries>>

    fun writeImproveDiaries(
        content: String,
        emotionDiaryId: Int,
        memberId: Int,
        emotionTags: List<String>,
        title: String
    ): Single<EmptyDto>
}