package com.lamnt.furniture.ui.forgot_password.enter_pin

import android.content.Context
import com.lamnt.furniture.R
import com.lamnt.furniture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class EnterPinViewModel @Inject constructor(@ApplicationContext context: Context) : BaseViewModel() {

    private val mContext : Context = context

    fun validate(otp : List<String>) : Boolean {
        for (item in otp){
            if (item.isEmpty()){
                setMessage(mContext.getString(R.string.pls_enter_otp))
                return false
            }
        }
        return true
    }

    fun getOtp(otp : List<String>): String {
        if (validate(otp)) {
            return otp.joinToString("")
        }
        return ""
    }
}