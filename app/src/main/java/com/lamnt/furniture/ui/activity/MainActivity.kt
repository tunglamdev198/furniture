package com.lamnt.furniture.ui.activity

import android.os.Bundle
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.ActivityMainBinding
import com.lamnt.furniture.extensions.click
import com.lamnt.furniture.extensions.replaceFragment
import com.lamnt.furniture.ui.base.BaseActivity
import com.lamnt.furniture.ui.main.account.AccountFragment
import com.lamnt.furniture.ui.main.cart.CartFragment
import com.lamnt.furniture.ui.main.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onViewReady(savedInstance: Bundle?) {
        replaceFragment(HomeFragment(), false)
        binding.mainBottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mnuMain -> {
                    replaceFragment(HomeFragment(), false)
                    true
                }

                R.id.mnuCart -> {
                    replaceFragment(CartFragment(), false)
                    true
                }

                R.id.mnuAccount -> {
                    replaceFragment(AccountFragment(), false)
                    true
                }

                else -> false

            }
        }

        binding.btnBack.click { onBackPressed() }
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frameContainer)
        if (currentFragment is CartFragment
            || currentFragment is AccountFragment
        ) {
            binding.mainBottomNav.selectedItemId = R.id.mnuMain
        } else {
            super.onBackPressed()
        }
    }
}