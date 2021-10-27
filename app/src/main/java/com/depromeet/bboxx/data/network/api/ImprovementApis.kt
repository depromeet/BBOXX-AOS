package com.depromeet.bboxx.data.network.api

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.dto.ImproveDiariesDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ImprovementApis {
    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("improvement-diaries")
    fun getImprovementDiaries()
    : Single<ImproveDiariesDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("improvement-diaries/keep")
    fun writeImprovement(@Body body: HashMap<String, Any>): Single<EmptyDto>

}