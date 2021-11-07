package com.depromeet.bboxx.domain.repository.auth

import com.depromeet.bboxx.domain.model.Token
import com.depromeet.bboxx.domain.model.TokenValid
import io.reactivex.rxjava3.core.Single

interface AuthSignRepository {
    fun signIn(authData: String, providerType: String): Single<Token>

    fun signUp(authData: String, nickName: String, providerType: String): Single<Token>

    fun validToken(token: String): Single<TokenValid>
}