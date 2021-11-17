package com.lamnt.furniture.ui.main.home

import android.os.Bundle
import com.lamnt.furniture.MainActivity
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentHomeBinding
import com.lamnt.furniture.extensions.*
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragmentMVVM<FragmentHomeBinding, HomeViewModel>() {
    private val categoriesAdapter by lazy {
        CategoriesAdapter()
    }

    private val productionsAdapter by lazy {
        HomeProductionAdapter()
    }

    override fun getLayoutId(): Int = R.layout.fragment_home
    override fun onViewReady(savedInstance: Bundle?) {
//        binding.rvCategories.setupHorizontal(categoriesAdapter)
        binding.rvProductions.setupGrid(productionsAdapter,2)
        viewModel.getHomeProductions()
    }

    override fun getViewModelClazz(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initSubscriber() {
        handleLiveData(viewModel.categories) {
            it?.let {
                categoriesAdapter.notifyDataChanged(it)
            }
        }

        observeData(viewModel.productions){
            productionsAdapter.notifyDataChanged(it)
        }
    }

    override fun onResume() {
        super.onResume()
        if (isMain()) {
            with((requireActivity() as MainActivity)) {
                showBottomBar(true)
            }
        }
    }
}