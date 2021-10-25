package com.depromeet.bboxx.data.repository

import com.depromeet.bboxx.data.mapper.TokenEntityMapper
import com.depromeet.bboxx.data.repository.authsign.AuthSignDataSource
import com.depromeet.bboxx.domain.model.Token
import com.depromeet.bboxx.domain.repository.auth.AuthSignRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthSignRepositoryImpl @Inject constructor(
    private val authSignDataSource: AuthSignDataSource,
    private val tokenEntityMapper: TokenEntityMapper
) : AuthSignRepository {

    override fun signIn(authData: String, providerType: String): Single<Token> {
        return authSignDataSource.authSignIn(authData, providerType)
            .map(tokenEntityMapper::trans)
    }

    override fun signUp(authData: String, nickName: String, providerType: String): Single<Token> {
        return authSignDataSource.authSignUp(authData, nickName, providerType)
            .map(tokenEntityMapper::trans)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthSignRepositoryModule {
    @Binds
    abstract fun bindAuthSignRepository(repositoryImpl: AuthSignRepositoryImpl): AuthSignRepository
}