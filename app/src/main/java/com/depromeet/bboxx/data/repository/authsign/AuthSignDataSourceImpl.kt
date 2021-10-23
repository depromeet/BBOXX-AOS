package com.depromeet.bboxx.data.repository.authsign

import com.depromeet.bboxx.data.entity.TokenEntity
import com.depromeet.bboxx.data.network.auth.AuthSignRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthSignDataSourceImpl @Inject constructor(
    private val authSignRemoteImpl: AuthSignRemoteImpl
) : AuthSignDataSource {
    override fun authSignIn(authData: String, providerType: String): Single<TokenEntity> {
        return authSignRemoteImpl.getAuthSignIn(authData, providerType)
    }

    override fun authSignUp(
        authData: String,
        nickName: String,
        providerType: String
    ): Single<TokenEntity> {
        return authSignRemoteImpl.getAuthSignUp(authData, nickName, providerType)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthSignDataSourceModule {
    @Binds
    abstract fun bindAuthSignDataSource(dataSourceImpl: AuthSignDataSourceImpl): AuthSignDataSource
}