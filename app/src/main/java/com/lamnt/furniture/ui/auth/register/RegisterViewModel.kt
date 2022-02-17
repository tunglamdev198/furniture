package com.lamnt.furniture.ui.auth.register

import android.content.Context
import android.text.TextUtils
import com.lamnt.furniture.R
import com.lamnt.furniture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(@ApplicationContext context: Context) : BaseViewModel() {

    private val mContext : Context = context

    fun validateUsername(username : String) : Boolean {
        if (TextUtils.isEmpty(username)) {
            setMessage(mContext.getString(R.string.name_required))
            return false
        }
        return true
    }

    fun validatePhoneNumber(phoneNumber : String) : Boolean {
        if (TextUtils.isEmpty(phoneNumber)) {
            setMessage(mContext.getString(R.string.pass_required))
            return false
        }
        return true
    }

    fun validatePassword(password : String) : Boolean {
        if (TextUtils.isEmpty(password)) {
            setMessage(mContext.getString(R.string.pass_required))
            return false
        }

        if (password.length < 8) {
            setMessage(mContext.getString(R.string.pass_than_8))
            return false
        }

        if (!password.contains(Regex("[A-Z]"))){
            setMessage(mContext.getString(R.string.pass_must_has_upper))
            return false
        }

        if (!password.contains(Regex("[a-z]"))){
            setMessage(mContext.getString(R.string.pass_must_has_lower))
            return false
        }

        if (!password.contains(Regex("[0-9]"))){
            setMessage(mContext.getString(R.string.pass_must_has_lower))
            return false
        }

        if (!password.contains(Regex("[^a-zA-Z0-9 ]"))){
            setMessage(mContext.getString(R.string.pass_must_has_special_char))
            return false
        }

        return true
    }
}