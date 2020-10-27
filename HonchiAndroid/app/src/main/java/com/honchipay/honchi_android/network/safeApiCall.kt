package com.honchipay.honchi_android.network

import retrofit2.Response

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResponceResult<T> {
    return try {
        val myResp = call.invoke()

        if (myResp.isSuccessful) {
            ResponceResult.Success(myResp.body(), myResp.code())
        } else {
            ResponceResult.Error(myResp.message())
        }
    } catch (e: Exception) {
        ResponceResult.Error(e.message.toString())
    }
}