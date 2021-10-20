package com.lamnt.furniture.ui.auth.login

import android.os.Bundle
import com.lamnt.furniture.MainActivity
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentLoginBinding
import com.lamnt.furniture.extensions.click
import com.lamnt.furniture.extensions.navigateTo
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragmentMVVM<FragmentLoginBinding, LoginViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun getViewModelClazz(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun onViewReady(savedInstance: Bundle?) {
        binding.btnSignIn.click {
            requireActivity().navigateTo(MainActivity::class.java, true)
        }
    }

    override fun initSubscriber() {

    }
}