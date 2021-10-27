package com.depromeet.bboxx.data.network.userinfo

import com.depromeet.bboxx.data.entity.NicknameEntity
import com.depromeet.bboxx.data.entity.UserInfoEntity
import com.depromeet.bboxx.data.network.api.UserInfoApis
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class UserInfoRemoteImpl @Inject constructor(
    private val userInfoApis: UserInfoApis
): UserInfoRemote {

    override fun getNickname(): Single<NicknameEntity> {
        return userInfoApis.getNickName().map {
            it.data
        }
    }

    override fun getMyPageInfo(): Single<UserInfoEntity> {
        return userInfoApis.getMe().map {
            it.data
        }
    }

    override fun updateNickname(memberId: Int, nickname: String): Single<UserInfoEntity> {
        return userInfoApis.updateNickname(memberId, nickname).map {
            it.data
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class UserInfoRemoteModule {
    @Binds
    @Singleton
    abstract fun bindUserInfoRemote(userInfoRemoteImpl: UserInfoRemoteImpl): UserInfoRemote
}