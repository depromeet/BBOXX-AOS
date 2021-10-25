package com.depromeet.bboxx.domain.usecases.improve

import com.depromeet.bboxx.domain.model.ImprovementTags
import com.depromeet.bboxx.domain.repository.improvement.ImprovementRepository
import javax.inject.Inject

class ImproveUseCase @Inject constructor(
    private val improvementRepository: ImprovementRepository
)  {
    fun getImproveDiaries() =
        improvementRepository.getImproveDiaries()

    fun writeImproveDiaries(
        content: String,
        emotionDiaryId: Int,
        memberId: Int,
        emotionTags: List<ImprovementTags>,
        title: String
    ) =
        improvementRepository.writeImproveDiaries(content, emotionDiaryId, memberId, emotionTags, title)
}