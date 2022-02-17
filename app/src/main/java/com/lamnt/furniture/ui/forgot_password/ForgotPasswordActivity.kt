package com.lamnt.furniture.ui.forgot_password

import android.os.Bundle
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.ActivityForgotPasswordBinding
import com.lamnt.furniture.extensions.replaceFragment
import com.lamnt.furniture.ui.base.BaseActivity
import com.lamnt.furniture.ui.forgot_password.enter_phone_number.EnterPhoneNumberFragment
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordActivity : BaseActivity<ActivityForgotPasswordBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_forgot_password

    override fun onViewReady(savedInstance: Bundle?) {
        replaceFragment(EnterPhoneNumberFragment(), false)
    }
}