package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.NotificationsEntity
import com.depromeet.bboxx.domain.model.Notifications
import javax.inject.Inject

class NoticeEntityMapper @Inject constructor() {
    fun transList(target: List<NotificationsEntity>): List<Notifications> = with(target) {
        return map{
            Notifications(
                it.createAt,
                it.emotionDiaryId,
                it.id, it.message?:"", it.receiverId, it.state, it.title?:"", it.updateAt
            )
        }
    }

    fun trans(target: NotificationsEntity): Notifications = with(target) {
        return Notifications(
            createAt,
            emotionDiaryId,
            id, message, receiverId, state, title, updateAt
        )
    }
}