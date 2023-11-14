package com.android.network.services

sealed class ApiResult<T>(val data:T?, val message:String?) {
    class Error<T>(message: String?):ApiResult<T>(null, message){}
    class Success<T>(data: T):ApiResult<T>(data,null){}
}