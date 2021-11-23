package com.depromeet.bboxx.presentation.ui.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.depromeet.bboxx.R
import com.depromeet.bboxx.presentation.ui.AppContext
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.setDataStringSharedPreference
import com.depromeet.bboxx.util.VersionUtils
import com.depromeet.bboxx.util.constants.SharedConstants
import com.depromeet.bboxx.util.constants.SharedConstants.C_FCM_MSG_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_FCM_MSG_SHARED
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.text.SimpleDateFormat
import java.util.*

class FCMMessageService: FirebaseMessagingService() {

    private var fcmMsg = ""

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        AppContext.applicationContext()?.let{
            initSharedPreference(it, SharedConstants.C_FCM_TOKEN_SHRED)
            setDataStringSharedPreference(
                token,
                SharedConstants.C_FCM_TOKEN_KEY
            )
        }

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.run{
            saveFcmMessage(this)
        }
    }

    private fun saveFcmMessage(message: RemoteMessage){
        message?.let{ message ->
            val emotionDiaryId = message.data?.let{
                it["emotionDiaryId"]
            }
            val fcmMsg = message.notification?.let{
                it.body
            }
            val id = message.senderId
            val sentTime = message.sentTime
            val today = Calendar.getInstance()
            val sendDateTime = SimpleDateFormat("MM.dd").format(today.time)

            Log.d("FCM", "$emotionDiaryId + $fcmMsg + $id + $sentTime + SendDateTime : $sendDateTime")

            sendNotification(getString(R.string.notice), fcmMsg)
        }
    }

    private fun sendNotification(title: String?, message: String?){
        AppContext.applicationContext()?.let{
            initSharedPreference(it, C_FCM_MSG_SHARED)
            if (title != null) {
                setDataStringSharedPreference(title, C_FCM_MSG_KEY)
            }
        }
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
                    setSmallIcon(R.mipmap.ic_launcher_bboxx)
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
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}
