package com.depromeet.bboxx.data.repository.notice

import com.depromeet.bboxx.data.entity.NotificationTokenEntity
import com.depromeet.bboxx.data.entity.NotificationsEntity
import com.depromeet.bboxx.data.network.notice.NoticeRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class NoticeDataSourceImpl @Inject constructor(
    private val noticeRemote: NoticeRemote
): NoticeDataSource {

    override fun getNotificationList(receiverId: Int): Single<List<NotificationsEntity>> {
        return noticeRemote.getNotificationList(receiverId)
    }

    override fun getNotificationInfo(ownerId: Int): Single<NotificationTokenEntity> {
        return noticeRemote.getNotificationInfo(ownerId)
    }

    override fun deRegisterNotification(ownerId: Int): Single<NotificationsEntity> {
        return noticeRemote.deRegisterNotification(ownerId)
    }

    override fun registerNotification(ownerId: Int, token: String): Single<NotificationsEntity> {
        return noticeRemote.registerNotification(ownerId,token)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class NoticeDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindNoticeDataSource(dataSourceImpl: NoticeDataSourceImpl): NoticeDataSource
}