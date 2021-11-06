package com.depromeet.bboxx.data.network.emotions

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.entity.EmotionDiaryEntity
import com.depromeet.bboxx.data.entity.RequestEmotionsEntity
import io.reactivex.rxjava3.core.Single

interface EmotionsRemote {
    fun requestEmotionsStatus(): Single<RequestEmotionsEntity>

    fun registerEmotion(categoryId: Int, content: String, emotionStatuses: List<Int>, memberId: Int, title: String): Single<EmptyDto>

    fun searchEmotion(emotionId: Int): Single<EmotionDiaryEntity>

    fun deleteEmotion(emotionId: Int): Single<EmptyDto>
}