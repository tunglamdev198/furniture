package com.lamnt.furniture.ui.main.home

import com.lamnt.furniture.data.repository.impl.CategoryRepository
import com.lamnt.furniture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(categoryRepository: CategoryRepository): BaseViewModel() {
    val categories = categoryRepository.getCategories()
}