package com.depromeet.bboxx.data.repository.improvement

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.depromeet.bboxx.data.network.improvement.ImprovementRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class ImprovementDataSourceImpl @Inject constructor(
    private val improvementRemote: ImprovementRemote
): ImprovementDataSource {

    override fun getImproveDiaries(memberId: Int, month: Int, year: Int): Single<List<ImprovementDiariesEntity>> {
        return improvementRemote.getImproveDiaries(memberId, month, year)
    }

    override fun writeImproveDiaries(
        content: String,
        emotionDiaryId: Int,
        memberId: Int,
        emotionTags: List<String>,
        title: String
    ): Single<EmptyDto> {
        return improvementRemote.writeImproveDiaries(
            content,
            emotionDiaryId,
            memberId,
            emotionTags,
            title
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ImprovementDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindImprovementDataSource(dataSource: ImprovementDataSourceImpl): ImprovementDataSource
}