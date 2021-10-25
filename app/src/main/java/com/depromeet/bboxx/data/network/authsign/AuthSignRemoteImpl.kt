package com.depromeet.bboxx.data.network.authsign

import com.depromeet.bboxx.data.entity.TokenEntity
import com.depromeet.bboxx.data.network.api.AuthApis
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthSignRemoteImpl @Inject constructor(
    private val authApis: AuthApis
) : AuthSignRemote {

    override fun getAuthSignIn(authData: String, providerType: String): Single<TokenEntity> {

        val body = HashMap<String,Any>().apply {
            put("authData",authData)
            put("providerType",providerType)
        }

        return authApis.getSignIn(body).map {
            it.data
        }
    }

    override fun getAuthSignUp(
        authData: String,
        nickName: String,
        providerType: String
    ): Single<TokenEntity> {

        val body = HashMap<String,Any>().apply {
            put("authData",authData)
            put("nickname",nickName)
            put("providerType",providerType)
        }

        return authApis.getSignUp(body).map {
            it.data
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthSignRemoteModule {
    @Binds
    abstract fun bindAuthSignRemote(authSignRemoteImpl: AuthSignRemoteImpl): AuthSignRemote
}