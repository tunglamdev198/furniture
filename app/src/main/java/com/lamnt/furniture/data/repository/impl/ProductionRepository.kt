package com.lamnt.furniture.data.repository.impl

import com.lamnt.furniture.data.remote.datasouce.ProductionDatasource
import javax.inject.Inject

class ProductionRepository @Inject constructor(private val productionDatasource: ProductionDatasource) {
    fun getHomeProductions() = productionDatasource.getHomeProductions()
}