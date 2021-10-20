package com.lamnt.furniture.ui.auth

import android.os.Bundle
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.ActivityAuthBinding
import com.lamnt.furniture.extensions.replaceFragment
import com.lamnt.furniture.ui.auth.login.LoginFragment
import com.lamnt.furniture.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_auth

    override fun onViewReady(savedInstance: Bundle?) {
        replaceFragment(LoginFragment(), true)
    }
}