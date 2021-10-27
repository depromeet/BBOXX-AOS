package com.depromeet.bboxx.data.repository.decibel

import com.depromeet.bboxx.data.entity.DecibelEntity
import com.depromeet.bboxx.data.entity.RegisterDecibelEntity
import com.depromeet.bboxx.data.network.decibel.DecibelRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class DecibelDataSourceImpl @Inject constructor(
    private val decibelRemote: DecibelRemote
) : DecibelDataSource {

    override fun registerDecibel(decibel: Int, memberId: Int): Single<RegisterDecibelEntity> {
        return decibelRemote.registerDecibel(decibel, memberId)
    }

    override fun requestDecibelInfo(decibelId: Int): Single<DecibelEntity> {
        return decibelRemote.requestDecibelInfo(decibelId)
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class DecibelDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindDecibelDataSource(dataSource: DecibelDataSourceImpl): DecibelDataSource
}