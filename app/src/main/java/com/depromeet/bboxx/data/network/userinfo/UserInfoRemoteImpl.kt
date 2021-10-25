package com.depromeet.bboxx.data.network.userinfo

import com.depromeet.bboxx.data.entity.NicknameEntity
import com.depromeet.bboxx.data.network.api.UserInfoApis
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserInfoRemoteImpl @Inject constructor(
    private val userInfoApis: UserInfoApis
): UserInfoRemote {

    override fun getNickname(): Single<NicknameEntity> {
        return userInfoApis.getNickName().map {
            it.data
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserInfoRemoteModule {
    @Binds
    abstract fun bindUserInfoRemote(userInfoRemoteImpl: UserInfoRemoteImpl): UserInfoRemote
}