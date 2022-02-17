package com.lamnt.furniture.ui.forgot_password.enter_pin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentEnterPinBinding
import com.lamnt.furniture.extensions.click
import com.lamnt.furniture.extensions.replaceFragment
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import com.lamnt.furniture.ui.forgot_password.enter_phone_number.EnterPhoneNumberFragment
import com.lamnt.furniture.ui.forgot_password.reset_password.ResetPasswordFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterPinFragment : BaseFragmentMVVM<FragmentEnterPinBinding, EnterPinViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_enter_pin

    override fun getViewModelClazz(): Class<EnterPinViewModel> = EnterPinViewModel::class.java

    override fun onViewReady(savedInstance: Bundle?) {
        binding.imgBack.click {
            activity?.onBackPressed()
        }
        setupOTPInputs()

        binding.btnConfirm.click {
            otp()
        }

    }

    fun otp(){
        var otp1 = binding.edtCode1.text.toString().trim()
        var otp2 = binding.edtCode2.text.toString().trim()
        var otp3 = binding.edtCode3.text.toString().trim()
        var otp4 = binding.edtCode4.text.toString().trim()

        var otpList : List<String> = listOf(otp1, otp2, otp3, otp4)

        if (viewModel.validate(otpList)) {
            replaceFragment(ResetPasswordFragment(),false)
        }
    }

    private fun imeOptionsNext(edt1 : EditText, edt2 : EditText) {
        edt1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isNotEmpty()) {
                    edt2.requestFocus()
                }else {
                    edt1.requestFocus()
                }
            }
        })
    }

    private fun setupOTPInputs() {
        imeOptionsNext(binding.edtCode1, binding.edtCode2)
        imeOptionsNext(binding.edtCode2, binding.edtCode3)
        imeOptionsNext(binding.edtCode3, binding.edtCode4)
    }

    override fun initSubscriber() {

    }
}