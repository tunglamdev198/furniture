package com.lamnt.furniture.data.repository.impl

import com.lamnt.furniture.data.remote.datasouce.CategoryDataSource
import com.lamnt.furniture.utils.performGetOperation
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryDataSource: CategoryDataSource) {
    fun getCategories() = categoryDataSource.getCategories()
}