package com.depromeet.bboxx.data.repository

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.mapper.ImprovementDiariesEntityMapper
import com.depromeet.bboxx.data.mapper.ImprovementTagsEntityMapper
import com.depromeet.bboxx.data.repository.improvement.ImprovementDataSource
import com.depromeet.bboxx.domain.model.ImprovementDiaries
import com.depromeet.bboxx.domain.model.ImprovementTags
import com.depromeet.bboxx.domain.repository.improvement.ImprovementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class ImprovementRepositoryImpl @Inject constructor(
    private val improvementDataSource: ImprovementDataSource,
    private val improvementDiariesEntityMapper: ImprovementDiariesEntityMapper,
    private val improvementTagsEntityMapper: ImprovementTagsEntityMapper
) : ImprovementRepository{

    override fun getImproveDiaries(): Single<List<ImprovementDiaries>> {
        return improvementDataSource.getImproveDiaries().map {
            Observable.fromIterable(it)
                .map { list -> improvementDiariesEntityMapper.trans(list) }
                .toList()
                .blockingGet()
        }
    }

    override fun writeImproveDiaries(
        content: String,
        emotionDiaryId: Int,
        memberId: Int,
        emotionTags: List<ImprovementTags>,
        title: String
    ): Single<EmptyDto> {
        return  improvementDataSource.writeImproveDiaries(
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
abstract class ImprovementRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindImprovementRepository(repositoryImpl: ImprovementRepositoryImpl): ImprovementRepository
}