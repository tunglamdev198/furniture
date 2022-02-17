package com.lamnt.furniture.ui.forgot_password.reset_password

import android.os.Bundle
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentResetPasswordBinding
import com.lamnt.furniture.extensions.click
import com.lamnt.furniture.extensions.navigateTo
import com.lamnt.furniture.extensions.replaceFragment
import com.lamnt.furniture.extensions.showToast
import com.lamnt.furniture.ui.auth.AuthActivity
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import com.lamnt.furniture.ui.forgot_password.ForgotPasswordActivity
import com.lamnt.furniture.ui.forgot_password.enter_phone_number.EnterPhoneNumberFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : BaseFragmentMVVM<FragmentResetPasswordBinding, ResetPasswordViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_reset_password

    override fun getViewModelClazz(): Class<ResetPasswordViewModel> = ResetPasswordViewModel::class.java

    override fun onViewReady(savedInstance: Bundle?) {
        binding.btnResetPassword.click {
            resetPassword()
        }
        binding.imgBack.click {
            activity?.onBackPressed()
        }
    }

    fun resetPassword() {
        var password = binding.edtNewPassword.text.toString().trim()
        var rePassword = binding.edtReEnterPassword.text.toString().trim()

        if (viewModel.validatePassword(password)) {
            if (viewModel.isMatchPassword(password, rePassword)) {
                showToast(getString(R.string.reset_pass_success))
                requireActivity().navigateTo(AuthActivity::class.java, false)
            }
        }
    }

    override fun initSubscriber() {

    }
}