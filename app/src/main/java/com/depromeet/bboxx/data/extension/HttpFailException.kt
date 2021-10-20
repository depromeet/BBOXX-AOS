package com.depromeet.bboxx.data.extension

class HttpFailException (val code:Int,override val message: String): Exception()