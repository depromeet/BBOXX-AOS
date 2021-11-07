package com.depromeet.bboxx.presentation.ui.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.depromeet.bboxx.R
import com.depromeet.bboxx.presentation.ui.feelhistory.FeelingHistoryFragment
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.text.SimpleDateFormat
import java.util.*

class FCMMessageService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        val fcmToken = token

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
    }

    private fun saveFcmMessage(message: RemoteMessage){
        message.data?.let{ data ->
            val msg = data["message"] ?: ""
            val title = data["title"] ?: msg

            val today = Calendar.getInstance()
            val sendDateUAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(today.time)
        }
    }

    private fun sendNotification(title: String?, message: String?){
        val notiManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notiManager.createNotificationChannel(
                NotificationChannel(
                    getString(R.string.notification_channel_id),
                    getString(R.string.notice),
                    NotificationManager.IMPORTANCE_HIGH
                )
                    .apply {
                        enableLights(true)
                        enableVibration(true)
                    })
        }
    }

    private fun getPendingIntent(): PendingIntent {
        return PendingIntent.getActivity(this, 9880,
            Intent(this, FeelingHistoryFragment::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}