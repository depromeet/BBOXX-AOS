package com.depromeet.bboxx.data.network.userinfo

import com.depromeet.bboxx.data.entity.NicknameEntity
import io.reactivex.rxjava3.core.Single

interface UserInfoRemote {
    fun getNickname(): Single<NicknameEntity>
}