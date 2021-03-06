package com.depromeet.bboxx.data.repository

import com.depromeet.bboxx.data.mapper.NicknameEntityMapper
import com.depromeet.bboxx.data.mapper.UserInfoEntityMapper
import com.depromeet.bboxx.data.repository.userinfo.UserInfoDataSource
import com.depromeet.bboxx.domain.model.Nickname
import com.depromeet.bboxx.domain.model.UserInfo
import com.depromeet.bboxx.domain.repository.userinfo.UserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class UserInfoRepositoryImpl @Inject constructor(
    private val userInfoDataSource: UserInfoDataSource,
    private val nicknameEntityMapper: NicknameEntityMapper,
    private val userInfoEntityMapper: UserInfoEntityMapper
): UserInfoRepository{

    override fun getRandomNickName(): Single<Nickname> {
        return userInfoDataSource.getNickname()
            .map(nicknameEntityMapper::trans)
    }

    override fun getMyPageInfo(): Single<UserInfo> {
        return userInfoDataSource.getMyPageInfo()
            .map(userInfoEntityMapper::trans)
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class UserInfoRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserInfoRepository(repositoryImpl: UserInfoRepositoryImpl): UserInfoRepository
}