package com.depromeet.bboxx.data.network.api

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.dto.RequestEmotionsDto
import com.depromeet.bboxx.data.dto.SearchEmotionDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface EmotionsApis {
    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("emotions")
    fun afterGetEmotions(): Single<RequestEmotionsDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("emotions")
    fun registerEmotions(@Body body: HashMap<String, Any>): Single<EmptyDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("emotions/{emotionId}")
    fun searchRequestEmotions(@Path("emotionId") emotionId: Int) : Single<SearchEmotionDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @DELETE("emotions")
    fun deleteEmotions(@Path("emotionId") emotionId: Int): Single<EmptyDto>

}