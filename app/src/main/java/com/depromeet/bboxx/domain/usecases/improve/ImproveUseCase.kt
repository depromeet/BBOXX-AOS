package com.depromeet.bboxx.domain.usecases.improve

import com.depromeet.bboxx.domain.repository.improvement.ImprovementRepository
import javax.inject.Inject

class ImproveUseCase @Inject constructor(
    private val improvementRepository: ImprovementRepository
)  {
    fun getImproveDiaries(memberId: Int, month: Int, year: Int) =
        improvementRepository.getImproveDiaries(memberId, month, year)

    fun writeImproveDiaries(
        content: String,
        emotionDiaryId: Int,
        memberId: Int,
        emotionTags: List<String>,
        title: String
    ) =
        improvementRepository.writeImproveDiaries(content, emotionDiaryId, memberId, emotionTags, title)
}