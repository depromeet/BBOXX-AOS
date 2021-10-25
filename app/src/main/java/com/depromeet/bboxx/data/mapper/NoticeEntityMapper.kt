package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.NotificationsEntity
import com.depromeet.bboxx.domain.model.Notifications
import javax.inject.Inject

class NoticeEntityMapper @Inject constructor() {
    fun trans(target: NotificationsEntity): Notifications = with(target) {
        return Notifications(
            createAt,
            emotionDiaryId,
            id, message, receiverId, state, title, updateAt
        )
    }
}