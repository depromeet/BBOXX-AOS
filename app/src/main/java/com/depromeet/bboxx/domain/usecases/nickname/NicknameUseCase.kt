package com.depromeet.bboxx.domain.usecases.nickname

import com.depromeet.bboxx.domain.repository.userinfo.UserInfoRepository
import javax.inject.Inject

class NicknameUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    fun getNickname() = userInfoRepository.getRandomNickName()
}