package com.lamnt.furniture.ui.main.detail

import android.os.Bundle
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentDetailBinding
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import com.lamnt.furniture.ui.main.home.HomeProductionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: BaseFragmentMVVM<FragmentDetailBinding, DetailViewModel>() {
    private val productionAdapter by lazy {
        HomeProductionAdapter()
    }

    override fun getLayoutId(): Int = R.layout.fragment_detail

    override fun getViewModelClazz(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun onViewReady(savedInstance: Bundle?) {

    }

    override fun initSubscriber() {

    }
}