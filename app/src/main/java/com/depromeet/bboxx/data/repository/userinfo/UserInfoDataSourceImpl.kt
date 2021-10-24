package com.depromeet.bboxx.data.repository.userinfo

import com.depromeet.bboxx.data.entity.NicknameEntity
import com.depromeet.bboxx.data.network.userinfo.UserInfoRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserInfoDataSourceImpl @Inject constructor(
    private val userInfoRemote: UserInfoRemote
) : UserInfoDataSource{

    override fun getNickname(): Single<NicknameEntity> {
        return userInfoRemote.getNickname()
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserInfoDataSourceModule {
    @Binds
    abstract fun bindUserInfoDataSource(dataSourceImpl: UserInfoDataSourceImpl): UserInfoDataSource
}