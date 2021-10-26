package com.depromeet.bboxx.data.repository.improvement

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.depromeet.bboxx.data.network.improvement.ImprovementRemote
import com.depromeet.bboxx.domain.model.ImprovementTags
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ImprovementDataSourceImpl @Inject constructor(
    private val improvementRemote: ImprovementRemote
): ImprovementDataSource {

    override fun getImproveDiaries(): Single<List<ImprovementDiariesEntity>> {
        return improvementRemote.getImproveDiaries()
    }

    override fun writeImproveDiaries(
        content: String,
        emotionDiaryId: Int,
        memberId: Int,
        emotionTags: List<ImprovementTags>,
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
@InstallIn(ViewModelComponent::class)
abstract class ImprovementDataSourceModule {
    @Binds
    abstract fun bindImprovementDataSource(dataSource: ImprovementDataSourceImpl): ImprovementDataSource
}