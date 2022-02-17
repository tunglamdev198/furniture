package com.lamnt.furniture.ui.main.account

import androidx.lifecycle.MutableLiveData
import com.lamnt.furniture.data.local.PreferenceRepository
import com.lamnt.furniture.model.dto.User
import com.lamnt.furniture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val preferenceRepository: PreferenceRepository) :
    BaseViewModel() {
    val isLoggedIn = !preferenceRepository.getToken().isNullOrEmpty()

    val user by lazy {
        MutableLiveData<User?>().apply { value = preferenceRepository.getUser() }
    }

    fun logout() {
        preferenceRepository.saveToken("")
        preferenceRepository.saveUser(null)
    }
}