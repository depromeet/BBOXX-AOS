package com.depromeet.bboxx.presentation.ui.result

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(var exception: Throwable) : Result<Nothing>()
    data class PagingError(var exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
    object Paging: Result<Nothing>()
    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
            is PagingError->"Error[exception=$exception]"
            Paging -> "Paging"
        }
    }
}


val Result<*>.succeeded
    get() = this is Result.Success && data != null
