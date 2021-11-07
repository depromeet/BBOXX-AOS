package com.depromeet.bboxx.data.network.api

import com.depromeet.bboxx.data.dto.TokenDto
import com.depromeet.bboxx.data.dto.TokenValidDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApis {
    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("auth/signin")
    fun getSignIn(@Body body: HashMap<String, Any>): Single<TokenDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("auth/signup")
    fun getSignUp(@Body body: HashMap<String, Any>): Single<TokenDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("auth/validate-jwt")
    fun getValidateJwt(@Body body: HashMap<String, Any>): Single<TokenValidDto>

}