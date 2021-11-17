package com.lamnt.furniture.data.remote

import com.lamnt.furniture.model.Category
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {
    @GET("/categories")
    fun getCategories(): Single<DataResponse<List<Category>>>
}