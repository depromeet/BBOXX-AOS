package com.depromeet.bboxx.data.repository.emotion

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.entity.EmotionSearchEntity
import com.depromeet.bboxx.data.entity.RequestEmotionsEntity
import com.depromeet.bboxx.data.network.emotions.EmotionsRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class EmotionDataSourceImpl @Inject constructor(
    private val emotionRemote: EmotionsRemote
) : EmotionDataSource {

    override fun requestEmotionStatus(): Single<RequestEmotionsEntity> {
        return emotionRemote.requestEmotionsStatus()
    }

    override fun registerEmotion(
        categoryId: Int,
        content: String,
        emotionStatuses: String,
        memberId: Int,
        title: String
    ): Single<EmptyDto> {
        return emotionRemote.registerEmotion(categoryId, content, emotionStatuses, memberId, title)
    }

    override fun searchEmotion(emotionId: Int): Single<EmotionSearchEntity> {
        return emotionRemote.searchEmotion(emotionId)
    }

    override fun deleteEmotion(emotionId: Int): Single<EmptyDto> {
        return emotionRemote.deleteEmotion(emotionId)
    }

}

@Module
@InstallIn(ViewModelComponent::class)
abstract class EmotionDataSourceModule {
    @Binds
    abstract fun bindEmotionDataSource(dataSource: EmotionDataSourceImpl): EmotionDataSource
}