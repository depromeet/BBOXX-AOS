package com.depromeet.bboxx.data.network.api

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.dto.NotificationTokenDto
import com.depromeet.bboxx.data.dto.NotificationsDto
import com.depromeet.bboxx.data.dto.NotificationsListDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface NoticeApis {
    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("notifications")
    fun getNotificationList(@Query("receiver_id") recevierId: Int): Single<NotificationsListDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("push-tokens")
    fun getPushTokens(@Query("ownerId") ownerId: Int): Single<NotificationTokenDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("push-tokens/deregister")
    fun deregisterNotification(@Body body: HashMap<String, Any>): Single<NotificationsDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("push-tokens/register")
    fun registerNotification(@Body body: HashMap<String, Any>): Single<NotificationsDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("notifications/send")
    fun sendNotificationTest(@Body body: HashMap<String, Any>): Single<EmptyDto>
}