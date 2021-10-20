package com.depromeet.bboxx.data.extension

open class ApiException(open val code: String,override val message: String): Exception()