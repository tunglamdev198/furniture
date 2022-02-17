package com.lamnt.furniture.ui.main.home

import android.os.Bundle
import android.view.View
import com.lamnt.furniture.ui.activity.MainActivity
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentHomeBinding
import com.lamnt.furniture.extensions.*
import com.lamnt.furniture.model.dto.Production
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import com.lamnt.furniture.ui.base.OnItemClickListener
import com.lamnt.furniture.ui.main.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragmentMVVM<FragmentHomeBinding, HomeViewModel>(),
    OnItemClickListener<Production> {
    private val categoriesAdapter by lazy {
        CategoriesAdapter()
    }

    private val bannerAdapter by lazy {
        BannerAdapter()
    }

    private val productionsAdapter by lazy {
        HomeProductionAdapter()
    }

    override fun getLayoutId(): Int = R.layout.fragment_home
    override fun onViewReady(savedInstance: Bundle?) {
//        binding.rvCategories.setupHorizontal(categoriesAdapter)
        binding.rvFavorites.setupHorizontal(productionsAdapter)
        binding.rvProductions.setupHorizontal(productionsAdapter)
        binding.vgBanner.adapter = bannerAdapter
        viewModel.getHomeProductions()
        viewModel.getBanners()
        productionsAdapter.onItemClickListener = this

    }

    override fun getViewModelClazz(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initSubscriber() {
//        handleLiveData(viewModel.categories) {
//            it?.let {
//                categoriesAdapter.notifyDataChanged(it)
//            }
//        }

        observeData(viewModel.productions) {
            productionsAdapter.notifyDataChanged(it)
        }

        observeData(viewModel.banners) {
            bannerAdapter.notifyDataChanged(it)
        }
    }

    override fun onResume() {
        super.onResume()
        if (isMain()) {
            with((requireActivity() as MainActivity)) {
                showBottomBar(true)
                changeTitle("Home")
            }
        }
    }

    override fun onItemClick(view: View?, data: Production, position: Int) {
        shareViewModel.production.value = data
        replaceFragment(DetailFragment(), true)
    }
}