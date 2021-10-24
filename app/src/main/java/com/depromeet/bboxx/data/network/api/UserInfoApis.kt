package com.depromeet.bboxx.data.network.api

import com.depromeet.bboxx.data.dto.NicknameDto
import com.depromeet.bboxx.data.dto.UserInfoDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface UserInfoApis {

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("api/v1/generate-member-nickname")
    fun getNickName(): Single<NicknameDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("api/v1/members")
    fun getUserInfo(@Query("memberId") memberId: String): Single<UserInfoDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @PUT("api/v1/members")
    fun updateNickname(@Body body: HashMap<String, Any>): Single<UserInfoDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("api/v1/me")
    fun getMe(): Single<UserInfoDto>

}