//package com.depromeet.bboxx.data.repository.notice
//
//import com.depromeet.bboxx.data.entity.NotificationTokenEntity
//import com.depromeet.bboxx.data.entity.NotificationsEntity
//import com.depromeet.bboxx.data.network.notice.NoticeRemote
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ViewModelComponent
//import io.reactivex.rxjava3.core.Single
//import javax.inject.Inject
//
//class NoticeDataSourceImpl @Inject constructor(
//    private val noticeRemote: NoticeRemote
//): NoticeDataSource {
//
//    override fun getNotificationList(): Single<List<NotificationsEntity>> {
//        return noticeRemote.getNotificationList()
//    }
//
//    override fun getNotificationInfo(ownerId: Int): Single<NotificationTokenEntity> {
//        return noticeRemote.getNotificationInfo(ownerId)
//    }
//
//    override fun deRegisterNotification(ownerId: Int): Single<NotificationsEntity> {
//        return noticeRemote.deRegisterNotification(ownerId)
//    }
//
//    override fun registerNotification(ownerId: Int, token: String): Single<NotificationsEntity> {
//        return noticeRemote.registerNotification(ownerId,token)
//    }
//}
//
//@Module
//@InstallIn(ViewModelComponent::class)
//abstract class NoticeDataSourceModule {
//    @Binds
//    abstract fun bindNoticeDataSource(dataSourceImpl: NoticeDataSourceImpl): NoticeDataSource
//}