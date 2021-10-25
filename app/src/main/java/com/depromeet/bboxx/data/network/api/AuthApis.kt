package com.depromeet.bboxx.data.network.api

import com.depromeet.bboxx.data.dto.TokenDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface AuthApis {
    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("api/v1/auth/signin")
    fun getSignIn(@Body body: HashMap<String, Any>): Single<TokenDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("api/v1/auth/signup")
    fun getSignUp(@Body body: HashMap<String, Any>): Single<TokenDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("api/v1/auth/validate-jwt")
    fun getValidateJwt(@Query("jwt") jwt: String): Single<TokenDto>


}