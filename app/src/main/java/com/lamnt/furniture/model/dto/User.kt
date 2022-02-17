package com.lamnt.furniture.model.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("userName")
    @Expose
    val userName: String,
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("phone")
    @Expose
    val phone: String,
    @SerializedName("address")
    @Expose
    val address: String,
    @SerializedName("avatar")
    @Expose
    val avatar: String,
    @SerializedName("birthDay")
    @Expose
    val birthDay: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("gender")
    @Expose
    val gender: String,
    @SerializedName("token")
    @Expose
    val token: String,
    @SerializedName("permission")
    @Expose
    val permission: String
)
