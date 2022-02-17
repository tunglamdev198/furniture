package com.lamnt.furniture.data.remote

import com.lamnt.furniture.model.Category
import com.lamnt.furniture.model.dto.User
import com.lamnt.furniture.model.dto.request.LoginRequest
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface ApiService {

    @POST("login")
    fun login(@Body loginRequest : LoginRequest): Single<DataResponse<User>>

    @GET("categories")
    fun getCategories(): Single<DataResponse<List<Category>>>
}