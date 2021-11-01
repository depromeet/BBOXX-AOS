package com.depromeet.bboxx.presentation.mapper

import com.depromeet.bboxx.domain.model.Notifications
import com.depromeet.bboxx.presentation.model.NotificationModel
import javax.inject.Inject

class NotificationsMapper @Inject constructor() {
    fun trans(target: List<Notifications>): List<NotificationModel> = with(target) {
        return map {
            NotificationModel(
                it.createAt,
                it.emotionDiaryId,
                it.id,
                it.message,
                it.receiverId,
                it.state,
                it.title,
                it.updateAt,
                isDelete = false
            )
        }
    }
}