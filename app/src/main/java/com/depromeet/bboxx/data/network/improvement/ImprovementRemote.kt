package com.depromeet.bboxx.data.network.improvement

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.depromeet.bboxx.domain.model.ImprovementTags
import io.reactivex.rxjava3.core.Single

interface ImprovementRemote {

    fun getImproveDiaries(): Single<List<ImprovementDiariesEntity>>

    fun writeImproveDiaries(
        content: String,
        emotionDiaryId: Int,
        memberId: Int,
        emotionTags: List<ImprovementTags>,
        title: String
    ): Single<EmptyDto>
}