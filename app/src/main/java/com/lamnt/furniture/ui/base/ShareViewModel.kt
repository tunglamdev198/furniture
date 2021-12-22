package com.lamnt.furniture.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lamnt.furniture.model.dto.Production
import com.lamnt.furniture.model.dto.User

class ShareViewModel : ViewModel() {
    val production = MutableLiveData<Production>()
    val user = MutableLiveData<User>()
}