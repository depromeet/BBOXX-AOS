package com.depromeet.bboxx.data.network.api

import com.depromeet.bboxx.data.dto.DecibelDto
import com.depromeet.bboxx.data.dto.RegisterDecibelDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface DecibelApis {

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("decibel")
    fun registerDecibel(@Body body: HashMap<String, Any>): Single<RegisterDecibelDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("decibel")
    fun requestDecibelInfo(@Query("decibelId") decibelId: Int): Single<DecibelDto>
}