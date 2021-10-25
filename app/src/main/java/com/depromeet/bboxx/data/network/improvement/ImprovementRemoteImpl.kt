package com.depromeet.bboxx.data.network.improvement

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.depromeet.bboxx.data.network.api.ImprovementApis
import com.depromeet.bboxx.domain.model.ImprovementTags
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ImprovementRemoteImpl @Inject constructor(
    private val improvementApis: ImprovementApis
) : ImprovementRemote {

    override fun getImproveDiaries(): Single<List<ImprovementDiariesEntity>> {
        return improvementApis.getImprovementDiaries().map {
            it.data
        }
    }

    override fun writeImproveDiaries(
        content: String,
        emotionDiaryId: Int,
        memberId: Int,
        emotionTags: List<ImprovementTags>,
        title: String
    ): Single<EmptyDto> {
        val body = HashMap<String, Any>().apply {
            put("authData", content)
            put("emotionDiaryId", emotionDiaryId)
            put("memberId", memberId)
            put("tags", emotionTags)
            put("title", title)
        }

        return improvementApis.writeImprovement(body)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class ImprovementRemoteModule {
    @Binds
    abstract fun bindImprovementRemote(remoteImpl: ImprovementRemoteImpl): ImprovementRemote
}