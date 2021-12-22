package com.lamnt.furniture.ui.main.checkout_bill

import androidx.lifecycle.MutableLiveData
import com.lamnt.furniture.data.database.dao.ProductionDao
import com.lamnt.furniture.data.database.entities.ProductionEntity
import com.lamnt.furniture.data.local.PreferenceRepository
import com.lamnt.furniture.model.dto.User
import com.lamnt.furniture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckoutBillViewModel @Inject constructor(
    private val productionDao: ProductionDao,
    private val preferenceRepository: PreferenceRepository
) :
    BaseViewModel() {
    val user by lazy {
        MutableLiveData<User?>().apply { value = preferenceRepository.getUser() }
    }
    val productions by lazy {
        MutableLiveData<List<ProductionEntity>>()
    }

    fun getAllProduction() {
        productions.value = productionDao.getAllProduction()
    }
}