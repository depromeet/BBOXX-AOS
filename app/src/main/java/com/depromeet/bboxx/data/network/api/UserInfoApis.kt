package com.depromeet.bboxx.data.network.api

import com.depromeet.bboxx.data.dto.NicknameDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserInfoApis {

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("api/v1/generate-member-nickname")
    fun getNickName(): Single<NicknameDto>
}