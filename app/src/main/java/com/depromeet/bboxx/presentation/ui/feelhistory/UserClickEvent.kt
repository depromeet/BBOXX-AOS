package com.depromeet.bboxx.presentation.ui.feelhistory

import com.depromeet.bboxx.presentation.model.NotificationModel

interface UserClickEvent {

    fun onItemClick(notifications: NotificationModel, position: Int)

    fun onItemDeleteClick(notifications: NotificationModel, position: Int)

}