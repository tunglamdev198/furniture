package com.lamnt.furniture.ui.main.cart

import androidx.lifecycle.MutableLiveData
import com.lamnt.furniture.data.database.dao.ProductionDao
import com.lamnt.furniture.data.database.entities.ProductionEntity
import com.lamnt.furniture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val productionDao: ProductionDao) :
    BaseViewModel() {
    val productions by lazy {
        MutableLiveData<List<ProductionEntity>>()
    }

    fun getAllProduction() {
        productions.value = productionDao.getAllProduction()
    }

    fun updateAmount(id: Int, amount: Int) {
        productionDao.updateAmount(id, amount)
    }

    fun removeProduction(id: Int) {
        productionDao.removeProduction(id)
    }
}