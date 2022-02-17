package com.lamnt.furniture.data.remote

data class Response<T>(val status: Int, val message: String,val data : T?)