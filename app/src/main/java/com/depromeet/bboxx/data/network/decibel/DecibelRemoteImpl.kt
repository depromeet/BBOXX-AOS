package com.depromeet.bboxx.data.network.decibel

import com.depromeet.bboxx.data.entity.DecibelEntity
import com.depromeet.bboxx.data.entity.RegisterDecibelEntity
import com.depromeet.bboxx.data.network.api.DecibelApis
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DecibelRemoteImpl @Inject constructor(
    private val decibelApis: DecibelApis
) : DecibelRemote {

    override fun registerDecibel(decibel: Int, memberId: Int): Single<RegisterDecibelEntity> {

        val body = HashMap<String, Any>().apply {
            put("decibel", decibel)
            put("memberId", memberId)
        }

        return decibelApis.registerDecibel(body).map {
            it.data
        }
    }

    override fun requestDecibelInfo(decibelId: Int): Single<DecibelEntity> {
        return decibelApis.requestDecibelInfo(decibelId).map {
            it.data
        }
    }
}


@Module
@InstallIn(ViewModelComponent::class)
abstract class DecibelRemoteModule {
    @Binds
    abstract fun bindDecibelRemote(decibelRemoteImpl: DecibelRemoteImpl): DecibelRemote
}