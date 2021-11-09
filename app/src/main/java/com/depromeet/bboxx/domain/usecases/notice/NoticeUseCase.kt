package com.depromeet.bboxx.domain.usecases.notice

import android.util.Log
import com.depromeet.bboxx.domain.repository.notice.NoticeRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class NoticeUseCase @Inject constructor(
    private val noticeRepository: NoticeRepository
) {
    fun getNotificationInfoList(receiverId: Int) =
        noticeRepository.getNotificationList(receiverId)

    fun getNotificationInfo(ownerId: Int) =
        noticeRepository.getNotificationInfo(ownerId)

    fun deRegisterNotification(ownerId: Int) =
        noticeRepository.deRegisterNotification(ownerId)

    fun registerNotification(ownerId: Int, token: String) =
        noticeRepository.registerNotification(ownerId, token)

    fun getFirebaseToken(): String {
        var token: String = ""
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            task.result

            Log.d("Firebase", token)
            // token 에서 : 나눠서 생각해보면 됨 앞에가 ID, 뒤에가 Token
            // https://cdmunoz.medium.com/fcm-getinstance-gettoken-in-android-is-now-deprecated-how-to-fix-it-3922a94f4fa4
            // https://firebase.google.com/docs/cloud-messaging/android/first-message
            // https://effectivecode.tistory.com/1080
        })
        return token
    }

    fun sendNotificationTest(emotionId: Int, ownerId: Int) =
        noticeRepository.sendNotificationTest(emotionId, ownerId)

}
