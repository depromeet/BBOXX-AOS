package com.depromeet.bboxx.data.network.api

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.dto.ImproveDiariesDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface ImprovementApis {
    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("improvement-diaries")
    fun getImprovementDiaries(@Query("member_id") member_id: Int,
                              @Query("month") month: Int, @Query("year") year: Int)
            : Single<ImproveDiariesDto>

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("improvement-diaries/keep")
    fun writeImprovement(@Body body: HashMap<String, Any>): Single<EmptyDto>

}