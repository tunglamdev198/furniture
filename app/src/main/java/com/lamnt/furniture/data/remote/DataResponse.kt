package com.lamnt.furniture.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataResponse<T>(
    @Expose @SerializedName("errorCode") val status: String,
    @Expose @SerializedName("message") val message: String?,
    @Expose @SerializedName("data") val data: T?
)