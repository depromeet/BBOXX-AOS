package com.depromeet.bboxx.data.repository

import com.depromeet.bboxx.data.mapper.DecibelEntityMapper
import com.depromeet.bboxx.data.mapper.RegisterDecibelEntityMapper
import com.depromeet.bboxx.data.repository.decibel.DecibelDataSource
import com.depromeet.bboxx.domain.model.DecibelInfo
import com.depromeet.bboxx.domain.model.RegisterDecibel
import com.depromeet.bboxx.domain.repository.decibel.DecibelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DecibelRepositoryImpl @Inject constructor(
    private val decibelDataSource: DecibelDataSource,
    private val registerDecibelEntityMapper: RegisterDecibelEntityMapper,
    private val decibelEntityMapper: DecibelEntityMapper
) : DecibelRepository {

    override fun registerDecibel(decibel: Int, memberId: Int): Single<RegisterDecibel> {
        return decibelDataSource.registerDecibel(decibel, memberId).map {
            registerDecibelEntityMapper.trans(it)
        }
    }

    override fun requestDecibelInfo(decibelId: Int): Single<DecibelInfo> {
        return decibelDataSource.requestDecibelInfo(decibelId).map {
            decibelEntityMapper.trans(it)
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class DecibelRepositoryModule {
    @Binds
    abstract fun bindDecibelRepository(repositoryImpl: DecibelRepositoryImpl): DecibelRepository
}