package com.depromeet.bboxx.domain.repository.notice

import com.depromeet.bboxx.domain.model.NotificationToken
import com.depromeet.bboxx.domain.model.Notifications
import io.reactivex.rxjava3.core.Single

interface NoticeRepository {
    fun getNotificationList(): Single<List<Notifications>>

    fun getNotificationInfo(ownerId: Int): Single<NotificationToken>

    fun deRegisterNotification(ownerId: Int): Single<Notifications>

    fun registerNotification(ownerId: Int, token: String): Single<Notifications>

}