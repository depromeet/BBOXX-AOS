package com.depromeet.bboxx.data.repository.userinfo

import com.depromeet.bboxx.data.entity.NicknameEntity
import io.reactivex.rxjava3.core.Single

interface UserInfoDataSource {

    fun getNickname(): Single<NicknameEntity>
}