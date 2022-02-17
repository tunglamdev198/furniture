package com.lamnt.furniture.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.lamnt.furniture.data.remote.Resource
import com.lamnt.furniture.data.repository.impl.CategoryRepository
import com.lamnt.furniture.data.repository.impl.ProductionRepository
import com.lamnt.furniture.model.Category
import com.lamnt.furniture.model.dto.Banner
import com.lamnt.furniture.model.dto.Production
import com.lamnt.furniture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val productionRepository: ProductionRepository
) :
    BaseViewModel() {
    val categories by lazy {
        MutableLiveData<Resource<List<Category>>>()
    }

    val productions by lazy {
        MutableLiveData<List<Production>>()
    }

    val banners by lazy {
        MutableLiveData<List<Banner>>()
    }

    fun getCategories() {
        loadApi(categoryRepository.getCategories()) {
            categories.value = it
        }
    }

    fun getHomeProductions() {
        productions.value = productionRepository.getHomeProductions()
    }

    fun getBanners() {
        banners.value = productionRepository.getBanner()
    }
}