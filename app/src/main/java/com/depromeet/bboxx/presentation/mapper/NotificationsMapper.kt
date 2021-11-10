package com.depromeet.bboxx.presentation.mapper

import android.annotation.SuppressLint
import com.depromeet.bboxx.domain.model.Notifications
import com.depromeet.bboxx.presentation.model.NotificationModel
import com.depromeet.bboxx.util.DateFormatter
import javax.inject.Inject

class NotificationsMapper @Inject constructor() {
    @SuppressLint("NewApi")
    fun trans(target: List<Notifications>): List<NotificationModel> = with(target) {
        return map {
            val createDate = it.createAt.substring(0, it.createAt.indexOf("."))
            NotificationModel(
                DateFormatter().formatFormatterCalc(createDate),
                it.emotionDiaryId,
                it.id,
                it.message,
                it.receiverId,
                it.state,
                it.title,
                it.updateAt,
                isDelete = false,
                DateFormatter().convertDateBefore(createDate)
                )
            }
        }
}
