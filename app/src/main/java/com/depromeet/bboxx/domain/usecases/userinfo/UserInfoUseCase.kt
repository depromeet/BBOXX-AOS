package com.depromeet.bboxx.domain.usecases.userinfo

import com.depromeet.bboxx.domain.repository.userinfo.UserInfoRepository
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    fun getNickname() = userInfoRepository.getRandomNickName()

    fun getMyPageInfo() = userInfoRepository.getMyPageInfo()
}