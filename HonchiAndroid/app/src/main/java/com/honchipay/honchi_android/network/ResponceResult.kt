package com.honchipay.honchi_android.network

sealed class ResponceResult<out T: Any> {
    data class Success<out T : Any>(val data: T?, val code: Int) : ResponceResult<T>()
    data class Error(val exception: String) : ResponceResult<Nothing>()
}