package com.depromeet.bboxx.data.network.improvement

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.depromeet.bboxx.data.network.api.ImprovementApis
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class ImprovementRemoteImpl @Inject constructor(
    private val improvementApis: ImprovementApis
) : ImprovementRemote {

    override fun getImproveDiaries(memberId: Int, month: Int, year: Int): Single<List<ImprovementDiariesEntity>> {
        return improvementApis.getImprovementDiaries(memberId, month, year).map {
            it.data
        }
    }

    override fun writeImproveDiaries(
        content: String,
        emotionDiaryId: Int,
        memberId: Int,
        emotionTags: List<String>,
        title: String
    ): Single<EmptyDto> {
        val body = HashMap<String, Any>().apply {
            put("content", content)
            put("emotionDiaryId", emotionDiaryId)
            put("memberId", memberId)
            put("tags", emotionTags)
            put("title", title)
        }

        return improvementApis.writeImprovement(body)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ImprovementRemoteModule {
    @Binds
    @Singleton
    abstract fun bindImprovementRemote(remoteImpl: ImprovementRemoteImpl): ImprovementRemote
}