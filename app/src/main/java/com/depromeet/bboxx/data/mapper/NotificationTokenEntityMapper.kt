package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.NotificationTokenEntity
import com.depromeet.bboxx.domain.model.NotificationToken
import javax.inject.Inject

class NotificationTokenEntityMapper @Inject constructor() {
    fun trans(target: NotificationTokenEntity) : NotificationToken = with(target){
        return NotificationToken(id, ownerId, state)
    }
}
