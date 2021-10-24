package com.lamnt.furniture.ui.auth.register

import android.os.Bundle
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentRegisterBinding
import com.lamnt.furniture.extensions.click
import com.lamnt.furniture.extensions.navigateTo
import com.lamnt.furniture.extensions.replaceFragment
import com.lamnt.furniture.extensions.showToast
import com.lamnt.furniture.ui.auth.AuthActivity
import com.lamnt.furniture.ui.auth.login.LoginFragment
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragmentMVVM<FragmentRegisterBinding, RegisterViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_register

    override fun getViewModelClazz(): Class<RegisterViewModel> = RegisterViewModel::class.java

    override fun onViewReady(savedInstance: Bundle?) {
        binding.btnCreateAccount.click {
            register()
        }
        binding.txtSignIn.click {
            replaceFragment(LoginFragment(),false)
        }
    }

    fun register() {
        var username = binding.edtUsername.text.toString().trim()
        var phoneNumber = binding.edtPhone.text.toString().trim()
        var password = binding.edtPassword.text.toString().trim()
        if (viewModel.validateUsername(username) &&
            viewModel.validatePhoneNumber(phoneNumber) &&
            viewModel.validatePassword(password)) {

            showToast(getString(R.string.reset_pass_success))
            replaceFragment(LoginFragment(), false)
        }
    }


    override fun initSubscriber() {

    }
}