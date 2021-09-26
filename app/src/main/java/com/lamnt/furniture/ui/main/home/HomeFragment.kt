package com.lamnt.furniture.ui.main.home

import android.os.Bundle
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentHomeBinding
import com.lamnt.furniture.extensions.handleLiveData
import com.lamnt.furniture.extensions.setupHorizontal
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragmentMVVM<FragmentHomeBinding, HomeViewModel>() {
    private val categoriesAdapter by lazy {
        CategoriesAdapter()
    }

    override fun getLayoutId(): Int = R.layout.fragment_home
    override fun onViewReady(savedInstance: Bundle?) {
        binding.rvCategories.setupHorizontal(categoriesAdapter)
    }

    override fun getViewModelClazz(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initSubscriber() {
        handleLiveData(viewModel.categories) {
            it?.let {
                categoriesAdapter.notifyDataChanged(it)
            }
        }
    }
}