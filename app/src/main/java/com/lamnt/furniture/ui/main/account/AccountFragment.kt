package com.lamnt.furniture.ui.main.account

import android.os.Bundle
import com.lamnt.furniture.ui.activity.MainActivity
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentAccountBinding
import com.lamnt.furniture.databinding.FragmentDetailBinding
import com.lamnt.furniture.extensions.changeTitle
import com.lamnt.furniture.extensions.showBottomBar
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import com.lamnt.furniture.ui.main.home.HomeProductionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment: BaseFragmentMVVM<FragmentAccountBinding, AccountViewModel>() {
    private val productionAdapter by lazy {
        HomeProductionAdapter()
    }

    override fun getLayoutId(): Int = R.layout.fragment_account

    override fun getViewModelClazz(): Class<AccountViewModel> = AccountViewModel::class.java

    override fun onViewReady(savedInstance: Bundle?) {
    }

    override fun initSubscriber() {

    }

    override fun onResume() {
        super.onResume()

        with((requireActivity() as MainActivity)) {
            showBottomBar(true)
            changeTitle("My Account")
        }
        
    }
}