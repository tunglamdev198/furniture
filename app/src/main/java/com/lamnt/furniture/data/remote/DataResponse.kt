package com.lamnt.furniture.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataResponse<T>(
    @Expose @SerializedName("status") val status: Int,
    @Expose @SerializedName("message") val message: String?,
    @Expose @SerializedName("token") val token: String?,
    @Expose @SerializedName("data") val data: T?
)