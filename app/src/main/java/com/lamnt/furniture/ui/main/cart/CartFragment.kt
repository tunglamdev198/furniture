package com.lamnt.furniture.ui.main.cart

import android.os.Bundle
import android.view.View
import com.lamnt.furniture.R
import com.lamnt.furniture.data.database.entities.ProductionEntity
import com.lamnt.furniture.databinding.FragmentCartBinding
import com.lamnt.furniture.extensions.*
import com.lamnt.furniture.ui.activity.MainActivity
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import com.lamnt.furniture.ui.base.OnItemClickListener
import com.lamnt.furniture.ui.main.checkout_bill.CheckoutBillFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragmentMVVM<FragmentCartBinding, CartViewModel>(),
    OnItemClickListener<ProductionEntity> {

    private val cartAdapter by lazy {
        CartAdapter().apply { onItemClickListener = this@CartFragment }
    }

    override fun getLayoutId(): Int = R.layout.fragment_cart

    override fun getViewModelClazz(): Class<CartViewModel> = CartViewModel::class.java

    override fun onViewReady(savedInstance: Bundle?) {
        binding.cartRecyclerView.setupVertical(cartAdapter)
        viewModel.getAllProduction()
        binding.btnCheckOut.click {
            replaceFragment(CheckoutBillFragment(), true)
        }
    }

    override fun initSubscriber() {
        observeData(viewModel.productions) {
            if (it.isNotEmpty()) {
                cartAdapter.notifyDataChanged(it)
                binding.layoutProduction.visible()
                binding.emptyCart.gone()
            } else {
                binding.layoutProduction.gone()
                binding.emptyCart.visible()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        with((requireActivity() as MainActivity)) {
            showBottomBar(true)
            changeTitle("Cart")
        }

    }

    override fun onItemClick(view: View?, data: ProductionEntity, position: Int) {
        when (view?.id) {
            R.id.btn_minus -> {
                if (data.amount == 1) {
                    viewModel.removeProduction(data.id)
                    viewModel.getAllProduction()
                } else {
                    data.amount = data.amount - 1
                    viewModel.updateAmount(data.id, data.amount)
                    cartAdapter.notifyItemChanged(position)
                }
            }

            R.id.btn_plus -> {
                data.amount = data.amount + 1
                viewModel.updateAmount(data.id, data.amount)
                cartAdapter.notifyItemChanged(position)
            }
        }
    }
}