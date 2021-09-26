package com.lamnt.furniture.data.remote

import com.lamnt.furniture.model.Category
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/categories")
    fun getCategories(): Response<List<Category>>
}