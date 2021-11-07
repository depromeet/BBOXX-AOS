package com.depromeet.bboxx.data.network.authsign

import com.depromeet.bboxx.data.entity.TokenEntity
import com.depromeet.bboxx.data.entity.TokenValidEntity
import io.reactivex.rxjava3.core.Single

interface AuthSignRemote {
    fun getAuthSignIn(authData: String, providerType: String): Single<TokenEntity>

    fun getAuthSignUp(authData: String, nickName: String, providerType: String): Single<TokenEntity>

    fun validToken(token: String): Single<TokenValidEntity>
}