package com.depromeet.bboxx.data.extension

import com.depromeet.bboxx.data.dto.BaseDto
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.HttpException
import java.io.IOException

fun <T> Observable<T>.applyApiResult(): Observable<T> {
    return this.compose { upStream->
        upStream.onErrorResumeNext { t:Throwable->
            when(t){
                is HttpException ->{
                    val errorCode = t.code()
                    val errorMsg = t.message()?:"UNKNOWN_ERROR_MESSAGE"
                    Observable.error(HttpFailException(errorCode,errorMsg))
                }
                is IOException ->{
                    Observable.error(NetworkException(t))
                }
                else->{
                    Observable.error(t)
                }
            }
        }.doOnNext {
            if(it is BaseDto){
                if(it.code != "200"){
                    throw ApiException(it.code,it.message)
                }
            }
        }
    }
}


fun <T> Single<T>.applyApiResult(): Single<T> {
    return this.compose { upStream->
        upStream.onErrorResumeNext { t:Throwable->
            when(t){
                is HttpException ->{
                    val errorCode = t.code()
                    val errorMsg = t.message()?:"UNKNOWN_ERROR_MESSAGE"
                    Single.error(HttpFailException(errorCode,errorMsg))
                }
                is IOException ->{
                    Single.error(NetworkException(t))
                }
                else->{
                    Single.error(t)
                }
            }
        }.doOnSuccess {
            if(it is BaseDto){
                if(it.code != "200"){
                    throw ApiException(it.code,it.message)
                }
            }
        }
    }
}

fun <T> Flowable<T>.applyApiResult(): Flowable<T> {
    return this.compose { upStream->
        upStream.onErrorResumeNext { t:Throwable->
            when(t){
                is HttpException ->{
                    val errorCode = t.code()
                    val errorMsg = t.message()?:"UNKNOWN_ERROR_MESSAGE"
                    Flowable.error(HttpFailException(errorCode,errorMsg))
                }
                is IOException ->{
                    Flowable.error(NetworkException(t))
                }
                else->{
                    Flowable.error(t)
                }
            }
        }.doOnNext {
            if(it is BaseDto){
                if(it.code != "200"){
                    throw ApiException(it.code,it.message)
                }
            }
        }
    }
}
