package com.depromeet.bboxx.data.repository

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.mapper.EmotionDiaryEntityMapper
import com.depromeet.bboxx.data.mapper.RequestEmotionEntityMapper
import com.depromeet.bboxx.data.repository.emotion.EmotionDataSource
import com.depromeet.bboxx.domain.model.EmotionDiary
import com.depromeet.bboxx.domain.model.RequestEmotions
import com.depromeet.bboxx.domain.repository.emotion.EmotionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class EmotionRepositoryImpl @Inject constructor(
    private val emotionDataSource: EmotionDataSource,
    private val requestEmotionStatusEntityMapper: RequestEmotionEntityMapper,
    private val emotionDiaryEntityMapper: EmotionDiaryEntityMapper
) : EmotionRepository {
    override fun requestEmotionStatus(): Single<RequestEmotions> {
        return emotionDataSource.requestEmotionStatus().map {
            requestEmotionStatusEntityMapper.trans(it)
        }
    }

    override fun registerEmotion(
        categoryId: Int,
        content: String,
        emotionStatuses: List<Int>,
        memberId: Int,
        title: String
    ): Single<EmptyDto> {
        return emotionDataSource.registerEmotion(
            categoryId,
            content,
            emotionStatuses,
            memberId,
            title
        )
    }

    override fun searchEmotion(emotionId: Int): Single<EmotionDiary> {
        return emotionDataSource.searchEmotion(emotionId).map {
            emotionDiaryEntityMapper.trans(it)
        }
    }

    override fun deleteEmotion(emotionId: Int): Single<EmptyDto> {
        return emotionDataSource.deleteEmotion(emotionId)
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class EmotionRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindEmotionRepository(repositoryImpl: EmotionRepositoryImpl): EmotionRepository
}