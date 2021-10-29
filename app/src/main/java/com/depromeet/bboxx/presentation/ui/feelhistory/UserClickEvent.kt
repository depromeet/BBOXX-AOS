package com.depromeet.bboxx.presentation.ui.feelhistory

import com.depromeet.bboxx.domain.model.Notifications

interface UserClickEvent {

    fun onItemClick(notifications: Notifications, position: Long)

    fun onItemDeleteClick(notifications: Notifications, position: Long)

}