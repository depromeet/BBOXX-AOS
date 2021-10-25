package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.UserInfoEntity
import com.depromeet.bboxx.domain.model.UserInfo
import javax.inject.Inject

class UserInfoEntityMapper @Inject constructor(){
    fun trans(target: UserInfoEntity) : UserInfo = with(target){
        return UserInfo(id, nickname, socialProviderType, state)
    }
}
