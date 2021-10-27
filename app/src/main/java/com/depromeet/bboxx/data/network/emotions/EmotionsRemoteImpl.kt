package com.depromeet.bboxx.data.network.emotions

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.entity.EmotionSearchEntity
import com.depromeet.bboxx.data.entity.RequestEmotionsEntity
import com.depromeet.bboxx.data.network.api.EmotionsApis
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class EmotionsRemoteImpl @Inject constructor(
    private val emotionsApis: EmotionsApis
) : EmotionsRemote {

    override fun requestEmotionsStatus(): Single<RequestEmotionsEntity> {
        return emotionsApis.afterGetEmotions().map {
            it.data
        }
    }

    override fun registerEmotion(
        categoryId: Int,
        content: String,
        emotionStatuses: String,
        memberId: Int,
        title: String
    ): Single<EmptyDto> {
        val body = HashMap<String, Any>().apply {
            put("categoryId", categoryId)
            put("content", content)
            put("emotionStatuses", emotionStatuses)
            put("memberId", memberId)
            put("title", title)
        }
        return emotionsApis.registerEmotions(body)
    }

    override fun searchEmotion(emotionId: Int): Single<EmotionSearchEntity> {
        return emotionsApis.searchRequestEmotions(emotionId).map {
            it.data
        }
    }

    override fun deleteEmotion(emotionId: Int): Single<EmptyDto> {
        return emotionsApis.deleteEmotions(emotionId)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class EmotionRemoteModule {
    @Binds
    @Singleton
    abstract fun bindEmotionRemote(remoteImpl: EmotionsRemoteImpl): EmotionsRemote
}