package com.depromeet.bboxx.data.network.nickname

import com.depromeet.bboxx.data.entity.NicknameEntity
import com.depromeet.bboxx.data.network.api.UserInfoApis
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NicknameRemoteImpl @Inject constructor(
    private val userInfoApis: UserInfoApis
): NicknameRemote {

    override fun getNickname(): Single<NicknameEntity> {
        return userInfoApis.getNickName().map {
            it.data
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class NicknameRemoteModule {
    @Binds
    abstract fun bindNicknameRemote(nicknameRemoteImpl: NicknameRemoteImpl): NicknameRemote
}