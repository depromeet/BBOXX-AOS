package com.depromeet.bboxx.presentation.ui.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.depromeet.bboxx.R
import com.depromeet.bboxx.presentation.ui.feelhistory.FeelingHistoryFragment
import com.depromeet.bboxx.util.VersionUtils
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

            sendNotification(title, msg)

            val today = Calendar.getInstance()
            val sendDateTime = SimpleDateFormat("MM.dd").format(today.time)

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
        val builder =
            NotificationCompat.Builder(this, getString(R.string.notification_channel_id))
                .apply {
                    setContentTitle(title)
                    setContentText(message)
                    setTicker(message ?: "")
                    setContentIntent(getPendingIntent())
                    //setSmallIcon(R.drawable.l_launcher)
                    setAutoCancel(true)
                    setWhen(System.currentTimeMillis())
                    setDefaults(Notification.DEFAULT_ALL)
                    setStyle(NotificationCompat.BigTextStyle().bigText(message))
                    setVibrate(longArrayOf(0, 500))
                    color = ContextCompat.getColor(this@FCMMessageService, R.color.main_bg)
                    setAutoCancel(true)
                    if (VersionUtils.isOverAPI21()) {
                        setCategory(Notification.CATEGORY_MESSAGE)
                        priority = NotificationCompat.PRIORITY_MAX
                        setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    }
                }


        notiManager.notify(1019, builder.build())
    }

    private fun getPendingIntent(): PendingIntent {
        return PendingIntent.getActivity(this, 1019,
            Intent(this, FeelingHistoryFragment::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}