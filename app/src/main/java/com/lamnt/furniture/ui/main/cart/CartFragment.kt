package com.lamnt.furniture.ui.main.cart

import android.os.Bundle
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentCartBinding
import com.lamnt.furniture.extensions.handleLiveData
import com.lamnt.furniture.extensions.setupHorizontal
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragmentMVVM<FragmentCartBinding, CartViewModel>() {

    private val cartAdapter by lazy {
        CartAdapter()
    }
    override fun getLayoutId(): Int = R.layout.fragment_cart

    override fun getViewModelClazz(): Class<CartViewModel> = CartViewModel::class.java

    override fun onViewReady(savedInstance: Bundle?) {
        binding.cartRecyclerView.setupHorizontal(cartAdapter)
    }

    override fun initSubscriber() {

    }
}