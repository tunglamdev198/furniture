package com.lamnt.furniture.ui.forgot_password.enter_phone_number

import android.content.Context
import android.text.TextUtils
import com.lamnt.furniture.R
import com.lamnt.furniture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class EnterPhoneNumberViewModel @Inject constructor(@ApplicationContext context: Context) : BaseViewModel() {

    private val mContext : Context = context

    fun validatePhoneNumber(phoneNumber:String) : Boolean {
        if (TextUtils.isEmpty(phoneNumber)) {
            setMessage(mContext.getString(R.string.pls_enter_phone_number))
            return false
        }
        return true

    }
}