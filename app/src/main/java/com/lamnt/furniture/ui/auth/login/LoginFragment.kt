package com.lamnt.furniture.ui.auth.login

import android.os.Bundle
import com.lamnt.furniture.BuildConfig
import com.lamnt.furniture.MainActivity
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentLoginBinding
import com.lamnt.furniture.extensions.*
import com.lamnt.furniture.ui.auth.register.RegisterFragment
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import com.lamnt.furniture.ui.forgot_password.ForgotPasswordActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragmentMVVM<FragmentLoginBinding, LoginViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun getViewModelClazz(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun onViewReady(savedInstance: Bundle?) {
        if (BuildConfig.DEBUG) {
            binding.edtUsername.setText("lamnt98")
            binding.edtPassword.setText("Lam@12345")
            postDelay(1000) { login() }
        }
        binding.btnSignIn.click {
            login()
        }
        binding.txtCreateAccount.click {
            switchToRegister()
        }
        binding.txtForgot.click {
            switchToForgotPass()
        }
    }

    private fun login() {

        val username = binding.edtUsername.toText()
        val password = binding.edtPassword.toText()

        if (viewModel.validateUsername(username) && viewModel.validatePassword(password)) {
            requireActivity().navigateTo(MainActivity::class.java, true)
            showInfo(getString(R.string.login_success))
        }
    }

    private fun switchToRegister() {
        replaceFragment(RegisterFragment(), true)
    }

    private fun switchToForgotPass() {
        requireActivity().navigateTo(ForgotPasswordActivity::class.java, false)
    }

    override fun initSubscriber() {

    }

    override fun onResume() {
        super.onResume()
        with(requireActivity() as MainActivity){
            changeTitle(R.string.title_home)
        }
    }
}