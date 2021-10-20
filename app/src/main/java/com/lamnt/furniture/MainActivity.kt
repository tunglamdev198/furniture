package com.lamnt.furniture

import android.os.Bundle
import com.lamnt.furniture.databinding.ActivityMainBinding
import com.lamnt.furniture.extensions.click
import com.lamnt.furniture.extensions.replaceFragment
import com.lamnt.furniture.ui.base.BaseActivity
import com.lamnt.furniture.ui.main.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onViewReady(savedInstance: Bundle?) {
        replaceFragment(HomeFragment(),false)
        binding.mainBottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mnuMain -> {
                    replaceFragment(HomeFragment(), false);
                    true
                }

                R.id.mnuCart ->
                    true

                R.id.mnuAccount ->
                    true

                else -> false

            }
        }

        binding.btnBack.click { onBackPressed() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}