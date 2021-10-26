package com.depromeet.bboxx.data.repository.decibel

import com.depromeet.bboxx.data.entity.DecibelEntity
import com.depromeet.bboxx.data.entity.RegisterDecibelEntity
import com.depromeet.bboxx.data.network.decibel.DecibelRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

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
@InstallIn(ViewModelComponent::class)
abstract class DecibelDataSourceModule {
    @Binds
    abstract fun bindDecibelDataSource(dataSource: DecibelDataSourceImpl): DecibelDataSource
}