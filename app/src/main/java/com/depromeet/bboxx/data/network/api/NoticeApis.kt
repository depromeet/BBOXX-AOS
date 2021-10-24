package com.depromeet.bboxx.data.network.api

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.dto.NotificationTokenDto
import com.depromeet.bboxx.data.dto.NotificationsDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface NoticeApis {
    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("api/v1/notifications")
    fun getFeelingList(@Query("receiver_id") receiver_id: Int): Single<NotificationsDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("api/v1/notifications/send")
    fun sendNotification(@Body body: HashMap<String, Any>): Single<EmptyDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("api/v1/push-tokens")
    fun getPushTokens(@Query("ownerId") ownerId: Int): Single<NotificationTokenDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("api/v1/push-tokens/deregister")
    fun deregisterImprovement(@Body body: HashMap<String, Any>): Single<NotificationsDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("api/v1/push-tokens/register")
    fun registerImprovement(@Body body: HashMap<String, Any>): Single<NotificationsDto>

}