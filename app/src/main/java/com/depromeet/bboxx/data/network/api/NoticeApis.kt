//package com.depromeet.bboxx.data.network.api
//
//import com.depromeet.bboxx.data.dto.NotificationTokenDto
//import com.depromeet.bboxx.data.dto.NotificationsDto
//import com.depromeet.bboxx.data.dto.NotificationsListDto
//import io.reactivex.rxjava3.core.Single
//import retrofit2.http.*
//
//interface NoticeApis {
//    @Headers("Content-type: application/json;charset=UTF-8")
//    @GET("api/v1/notifications")
//    fun getNotificationList(): Single<NotificationsListDto>
//
//    @Headers("Content-type: application/json;charset=UTF-8")
//    @GET("api/v1/push-tokens")
//    fun getPushTokens(@Query("ownerId") ownerId: Int): Single<NotificationTokenDto>
//
//    @Headers("Content-type: application/json;charset=UTF-8")
//    @POST("api/v1/push-tokens/deregister")
//    fun deregisterNotification(@Body body: HashMap<String, Any>): Single<NotificationsDto>
//
//    @Headers("Content-type: application/json;charset=UTF-8")
//    @POST("api/v1/push-tokens/register")
//    fun registerNotification(@Body body: HashMap<String, Any>): Single<NotificationsDto>
//
//}