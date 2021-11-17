package com.lamnt.furniture.data.remote.datasouce

import com.lamnt.furniture.data.remote.ApiService
import javax.inject.Inject

class CategoryDataSource @Inject constructor(private val service: ApiService) : BaseDataSource() {
    fun getCategories() = service.getCategories()
}