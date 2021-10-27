package com.depromeet.bboxx.presentation.ui.feelhistory

import com.depromeet.bboxx.domain.model.Notifications

interface UserClickEvent {

    fun onItemClick(notifications: Notifications)

    fun onItemDeleteClick(notifications: Notifications)

}