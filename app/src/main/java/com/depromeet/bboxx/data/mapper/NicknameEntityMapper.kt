package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.NicknameEntity
import com.depromeet.bboxx.domain.model.Nickname
import javax.inject.Inject

class NicknameEntityMapper @Inject constructor() {
    fun trans(target: NicknameEntity) : Nickname = with(target){
        return Nickname(nickName)
    }
}