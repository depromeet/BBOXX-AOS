package com.depromeet.bboxx.domain.usecases.emotion

import com.depromeet.bboxx.domain.repository.emotion.EmotionRepository
import javax.inject.Inject

class EmotionUseCase @Inject constructor(
    private val emotionRepository: EmotionRepository
){
    fun requestEmotionStatus() =
        emotionRepository.requestEmotionStatus()

    fun registerEmotion(categoryId: Int, content: String, emotionStatuses: String, memberId: Int, title: String) =
        emotionRepository.registerEmotion(categoryId, content, emotionStatuses, memberId, title)

    fun searchEmotion(emotionId: Int) =
        emotionRepository.searchEmotion(emotionId)

    fun deleteEmotion(emotionId: Int) =
        emotionRepository.deleteEmotion(emotionId)
}