package com.depromeet.bboxx.data.network.improvement

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import io.reactivex.rxjava3.core.Single

interface ImprovementRemote {

    fun getImproveDiaries(memberId: Int, month: Int, year: Int): Single<List<ImprovementDiariesEntity>>

    fun writeImproveDiaries(
        content: String,
        emotionDiaryId: Int,
        memberId: Int,
        emotionTags: List<String>,
        title: String
    ): Single<EmptyDto>
}