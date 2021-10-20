package com.depromeet.bboxx.data.network.nickname

import com.depromeet.bboxx.data.entity.NicknameEntity
import io.reactivex.rxjava3.core.Single

interface NicknameRemote {
    fun getNickname(): Single<NicknameEntity>
}