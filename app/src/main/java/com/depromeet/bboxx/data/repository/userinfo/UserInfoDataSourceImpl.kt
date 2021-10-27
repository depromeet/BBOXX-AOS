package com.depromeet.bboxx.data.repository.userinfo

import com.depromeet.bboxx.data.entity.NicknameEntity
import com.depromeet.bboxx.data.entity.UserInfoEntity
import com.depromeet.bboxx.data.network.userinfo.UserInfoRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class UserInfoDataSourceImpl @Inject constructor(
    private val userInfoRemote: UserInfoRemote
) : UserInfoDataSource{

    override fun getNickname(): Single<NicknameEntity> {
        return userInfoRemote.getNickname()
    }

    override fun getMyPageInfo(): Single<UserInfoEntity> {
        return userInfoRemote.getMyPageInfo()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class UserInfoDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindUserInfoDataSource(dataSourceImpl: UserInfoDataSourceImpl): UserInfoDataSource
}