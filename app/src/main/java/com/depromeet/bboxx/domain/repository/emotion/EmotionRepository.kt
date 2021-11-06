package com.depromeet.bboxx.domain.repository.emotion

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.domain.model.EmotionDiary
import com.depromeet.bboxx.domain.model.RequestEmotions
import io.reactivex.rxjava3.core.Single

interface EmotionRepository {
    fun requestEmotionStatus(): Single<RequestEmotions>

    fun registerEmotion(categoryId: Int, content: String, emotionStatuses: List<Int>, memberId: Int, title: String): Single<EmptyDto>

    fun searchEmotion(emotionId: Int): Single<EmotionDiary>

    fun deleteEmotion(emotionId: Int): Single<EmptyDto>
}