package com.depromeet.bboxx.data.network.api

import com.depromeet.bboxx.data.dto.EmptyDto
import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface ImprovementApis {
    @Headers("Content-type: application/json;charset=UTF-8")
    @GET("api/v1/improvement-diaries")
    fun getImprovementDiaries(@Query("member_id") memberId: Int
    ,@Query("month") month: Int, @Query("year") year: Int)
    : Single<ImprovementDiariesEntity>

    @Headers("Content-type: application/json;charset=UTF-8")
    @POST("api/v1/improvement-diaries/keep")
    fun writeImprovement(@Body body: HashMap<String, Any>): Single<EmptyDto>

}