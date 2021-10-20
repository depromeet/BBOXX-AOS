package com.depromeet.bboxx.data.repository

import com.depromeet.bboxx.data.mapper.NicknameEntityMapper
import com.depromeet.bboxx.data.repository.userinfo.UserInfoDataSource
import com.depromeet.bboxx.domain.model.Nickname
import com.depromeet.bboxx.domain.repository.userinfo.UserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val userInfoDataSource: UserInfoDataSource,
    private val nicknameEntityMapper: NicknameEntityMapper
): UserInfoRepository{

    override fun getRandomNickName(): Single<Nickname> {
        return userInfoDataSource.getNickname()
            .map(nicknameEntityMapper::trans)
    }

}

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserInfoRepositoryModule {
    @Binds
    abstract fun bindUserInfoRepository(repositoryImpl: UserInfoRepositoryImpl): UserInfoRepository
}