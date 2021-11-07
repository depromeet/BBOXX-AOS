package com.depromeet.bboxx.data.repository

import com.depromeet.bboxx.data.mapper.TokenEntityMapper
import com.depromeet.bboxx.data.mapper.TokenValidEntityMapper
import com.depromeet.bboxx.data.repository.authsign.AuthSignDataSource
import com.depromeet.bboxx.domain.model.Token
import com.depromeet.bboxx.domain.model.TokenValid
import com.depromeet.bboxx.domain.repository.auth.AuthSignRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class AuthSignRepositoryImpl @Inject constructor(
    private val authSignDataSource: AuthSignDataSource,
    private val tokenEntityMapper: TokenEntityMapper,
    private val tokenValidEntityMapper: TokenValidEntityMapper
) : AuthSignRepository {

    override fun signIn(authData: String, providerType: String): Single<Token> {
        return authSignDataSource.authSignIn(authData, providerType)
            .map(tokenEntityMapper::trans)
    }

    override fun signUp(authData: String, nickName: String, providerType: String): Single<Token> {
        return authSignDataSource.authSignUp(authData, nickName, providerType)
            .map(tokenEntityMapper::trans)
    }

    override fun validToken(token: String): Single<TokenValid> {
        return authSignDataSource.validToken(token)
            .map(tokenValidEntityMapper::trans)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthSignRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthSignRepository(repositoryImpl: AuthSignRepositoryImpl): AuthSignRepository
}