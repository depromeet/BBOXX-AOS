package com.depromeet.bboxx.data.network.api

import com.depromeet.bboxx.data.dto.NicknameDto
import com.depromeet.bboxx.data.dto.UserInfoDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface UserInfoApis {

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("generate-member-nickname")
    fun getNickName(): Single<NicknameDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("members")
    fun getUserInfo(@Query("memberId") memberId: String): Single<UserInfoDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @PUT("members/{memberId}")
    fun updateNickname(@Path("memberId") memberId: Int, @Field("nickname") nickname: String): Single<UserInfoDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("me")
    fun getMe(): Single<UserInfoDto>

}