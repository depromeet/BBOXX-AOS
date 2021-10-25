package com.depromeet.bboxx.data.repository

import com.depromeet.bboxx.data.mapper.NoticeEntityMapper
import com.depromeet.bboxx.data.mapper.NotificationTokenEntityMapper
import com.depromeet.bboxx.data.repository.notice.NoticeDataSource
import com.depromeet.bboxx.domain.model.NotificationToken
import com.depromeet.bboxx.domain.model.Notifications
import com.depromeet.bboxx.domain.repository.notice.NoticeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val noticeDataSource: NoticeDataSource,
    private val noticeEntityMapper: NoticeEntityMapper,
    private val notificationTokenEntityMapper: NotificationTokenEntityMapper
    ): NoticeRepository {
    override fun getNotificationList(): Single<List<Notifications>> {
        return noticeDataSource.getNotificationList().map {
            Observable.fromIterable(it)
                .map { list -> noticeEntityMapper.trans(list) }
                .toList()
                .blockingGet()
        }
    }

    override fun getNotificationInfo(ownerId: Int): Single<NotificationToken> {
        return noticeDataSource.getNotificationInfo(ownerId).map(notificationTokenEntityMapper::trans)
    }

    override fun deRegisterNotification(ownerId: Int): Single<Notifications> {
        return noticeDataSource.deRegisterNotification(ownerId).map(noticeEntityMapper::trans)
    }

    override fun registerNotification(ownerId: Int, token: String): Single<Notifications> {
        return noticeDataSource.registerNotification(ownerId, token).map(noticeEntityMapper::trans)
    }
}


@Module
@InstallIn(ViewModelComponent::class)
abstract class NoticeRepositoryModule {
    @Binds
    abstract fun bindNoticeRepository(repositoryImpl: NoticeRepositoryImpl): NoticeRepository
}