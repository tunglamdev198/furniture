package com.lamnt.furniture.ui.main.checkout_bill

import android.os.Bundle
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.LayoutCheckoutBillBinding
import com.lamnt.furniture.extensions.*
import com.lamnt.furniture.ui.activity.MainActivity
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import com.lamnt.furniture.ui.main.account.EditAccountFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutBillFragment : BaseFragmentMVVM<LayoutCheckoutBillBinding, CheckoutBillViewModel>() {

    private val productionCheckoutAdapter by lazy {
        ProductionCheckoutAdapter()
    }

    override fun getLayoutId(): Int = R.layout.layout_checkout_bill

    override fun getViewModelClazz(): Class<CheckoutBillViewModel> =
        CheckoutBillViewModel::class.java

    override fun onViewReady(savedInstance: Bundle?) {
        binding.rvProductions.setupVertical(productionCheckoutAdapter)
        viewModel.getAllProduction()
        binding.btnProceed.click { replaceFragment(CheckoutSucceedFragment(), false) }
        binding.btnEditDeliveryInfo.click {
            replaceFragment(EditAccountFragment(), true)
        }
    }

    override fun initSubscriber() {
        observeData(viewModel.productions) {
            productionCheckoutAdapter.notifyDataChanged(it)
            calculatePrice()
        }

        viewModel.user.value?.let {
            shareViewModel.user.value = it
            binding.user = it
        }
    }

    private fun calculatePrice() {
        var totalPrice = 0f
        for (product in productionCheckoutAdapter.data) {
            totalPrice += product.amount * product.price
        }
        binding.txtTotalPrice.text = String.format("%.2f", totalPrice).replace(",", ".")
    }

    override fun onResume() {
        super.onResume()

        with((requireActivity() as MainActivity)) {
            showBottomBar(false)
            changeTitle("Checkout")
        }

    }
}