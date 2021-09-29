package com.depromeet.bboxx.presentation.extension

fun <T> List<T>.getItem(index: Int): T?{
    if(index in 0 until size){
        return get(index)
    }
    return null
}