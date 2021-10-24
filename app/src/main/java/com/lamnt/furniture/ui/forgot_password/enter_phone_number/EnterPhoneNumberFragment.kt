package com.lamnt.furniture.ui.forgot_password.enter_phone_number

import android.os.Bundle
import android.text.TextUtils
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentEnterPhoneNumberBinding
import com.lamnt.furniture.extensions.click
import com.lamnt.furniture.extensions.navigateTo
import com.lamnt.furniture.extensions.replaceFragment
import com.lamnt.furniture.ui.auth.AuthActivity
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import com.lamnt.furniture.ui.forgot_password.enter_pin.EnterPinFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterPhoneNumberFragment : BaseFragmentMVVM<FragmentEnterPhoneNumberBinding, EnterPhoneNumberViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_enter_phone_number

    override fun getViewModelClazz(): Class<EnterPhoneNumberViewModel> = EnterPhoneNumberViewModel::class.java

    override fun onViewReady(savedInstance: Bundle?) {
        binding.imgBack.click {
            activity?.onBackPressed()
        }
        binding.btnSendSMS.click {
            sendSMS()
        }
    }
    private fun sendSMS(){
        var phoneNumber = binding.edtPhoneNumber.text.toString().trim()

        if (viewModel.validatePhoneNumber(phoneNumber)) {
            replaceFragment(EnterPinFragment(),true)
        }
    }

    override fun initSubscriber() {

    }
}