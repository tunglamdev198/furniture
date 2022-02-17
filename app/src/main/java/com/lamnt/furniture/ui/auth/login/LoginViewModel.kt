package com.lamnt.furniture.ui.auth.login

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.lamnt.furniture.R
import com.lamnt.furniture.data.local.PreferenceRepository
import com.lamnt.furniture.data.remote.Resource
import com.lamnt.furniture.data.repository.impl.UserRepository
import com.lamnt.furniture.model.dto.User
import com.lamnt.furniture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class LoginViewModel @Inject constructor(
    @ApplicationContext private val mContext: Context,
    private val preferenceRepository: PreferenceRepository,
    private val userRepository: UserRepository
) :
    BaseViewModel() {
    val userSource by lazy {
        MutableLiveData<Resource<User>>()
    }

    fun validateUsername(username: String): Boolean {
        if (TextUtils.isEmpty(username)) {
            setMessage(mContext.getString(R.string.name_required))
            return false
        }
        return true
    }

    fun validatePassword(password: String): Boolean {

        if (TextUtils.isEmpty(password)) {
            setMessage(mContext.getString(R.string.pass_required))
            return false
        }

        if (password.length < 6) {
            setMessage(mContext.getString(R.string.pass_than_8))
            return false
        }

        if (!password.contains(Regex("[A-Z]"))) {
            setMessage(mContext.getString(R.string.pass_must_has_upper))
            return false
        }

        if (!password.contains(Regex("[a-z]"))) {
            setMessage(mContext.getString(R.string.pass_must_has_lower))
            return false
        }

        if (!password.contains(Regex("[0-9]"))) {
            setMessage(mContext.getString(R.string.pass_must_has_lower))
            return false
        }

        if (!password.contains(Regex("[^a-zA-Z0-9 ]"))) {
            setMessage(mContext.getString(R.string.pass_must_has_special_char))
            return false
        }

        return true
    }

    fun login(username: String, password: String) {
        loadApi(userRepository.login(username, password)) {
            userSource.postValue(it)
        }
    }

    fun handleLogin(user: User, success: () -> Unit) {
        preferenceRepository.saveToken(user.token)
        preferenceRepository.saveUser(user)
        success()
    }

}