package com.depromeet.bboxx.domain.repository.userinfo

import com.depromeet.bboxx.domain.model.Nickname
import com.depromeet.bboxx.domain.model.UserInfo
import io.reactivex.rxjava3.core.Single

interface UserInfoRepository {
    fun getRandomNickName(): Single<Nickname>

    fun getMyPageInfo(): Single<UserInfo>
}
