package com.depromeet.bboxx.data.repository.emotion

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.entity.EmotionSearchEntity
import com.depromeet.bboxx.data.entity.RequestEmotionsEntity
import io.reactivex.rxjava3.core.Single

interface EmotionDataSource {

    fun requestEmotionStatus(): Single<RequestEmotionsEntity>

    fun registerEmotion(categoryId: Int, content: String, emotionStatuses: String, memberId: Int, title: String): Single<EmptyDto>

    fun searchEmotion(emotionId: Int): Single<EmotionSearchEntity>

    fun deleteEmotion(emotionId: Int): Single<EmptyDto>
}