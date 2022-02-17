package com.lamnt.furniture.ui.main.checkout_bill

import android.os.Bundle
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentCheckoutSucceedBinding
import com.lamnt.furniture.extensions.postDelay
import com.lamnt.furniture.extensions.showBottomBar
import com.lamnt.furniture.extensions.showNavigate
import com.lamnt.furniture.ui.activity.MainActivity
import com.lamnt.furniture.ui.base.BaseFragment

class CheckoutSucceedFragment : BaseFragment<FragmentCheckoutSucceedBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_checkout_succeed

    override fun onViewReady(savedInstance: Bundle?) {
        postDelay(3000) {
            requireActivity().onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        with((requireActivity() as MainActivity)) {
            showBottomBar(false)
            showNavigate(false)
        }

    }
}