package com.depromeet.bboxx.data.repository.authsign

import com.depromeet.bboxx.data.entity.TokenEntity
import io.reactivex.rxjava3.core.Single

interface AuthSignDataSource {

    fun authSignIn(authData: String, providerType: String): Single<TokenEntity>

    fun authSignUp(authData: String, nickName: String, providerType: String): Single<TokenEntity>

}