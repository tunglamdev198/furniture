package com.lamnt.furniture.ui.main.detail

import com.lamnt.furniture.data.database.dao.ProductionDao
import com.lamnt.furniture.data.database.entities.ProductionEntity
import com.lamnt.furniture.model.dto.Production
import com.lamnt.furniture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val productionDao: ProductionDao) :
    BaseViewModel() {
    fun addToCart(production: Production) {
        if (productionDao.exits(production.id)) {
            productionDao.updateAmount(
                production.id,
                productionDao.getCurrentProductionAmount(production.id) + 1
            )
        } else {
            val productionEntity = ProductionEntity(
                production.id,
                production.brandId,
                production.name,
                production.price,
                1,
                production.image ?: ""
            )
            productionDao.insertProduction(productionEntity)
        }
    }
}