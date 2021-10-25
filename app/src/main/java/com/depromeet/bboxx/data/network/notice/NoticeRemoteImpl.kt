package com.depromeet.bboxx.data.network.notice

import com.depromeet.bboxx.data.entity.NotificationTokenEntity
import com.depromeet.bboxx.data.entity.NotificationsEntity
import com.depromeet.bboxx.data.network.api.NoticeApis
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NoticeRemoteImpl @Inject constructor(
    private val noticeApi: NoticeApis
) : NoticeRemote{

    override fun getNotificationList(): Single<List<NotificationsEntity>> {
        return noticeApi.getNotificationList().map {
            it.data
        }
    }

    override fun getNotificationInfo(ownerId: Int): Single<NotificationTokenEntity> {
        return noticeApi.getPushTokens(ownerId).map {
            it.data
        }
    }

    override fun deRegisterNotification(ownerId: Int): Single<NotificationsEntity> {
        val body = HashMap<String, Any>().apply {
            put("ownerId", ownerId)
        }

        return noticeApi.deregisterNotification(body).map {
            it.data
        }
    }

    override fun registerNotification(ownerId: Int, token: String): Single<NotificationsEntity> {
        val body = HashMap<String, Any>().apply {
            put("ownerId", ownerId)
            put("token", token)
        }

        return noticeApi.registerNotification(body).map {
            it.data
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class NoticeRemoteModule {
    @Binds
    abstract fun bindNoticeRemote(remoteImpl: NoticeRemoteImpl): NoticeRemote
}