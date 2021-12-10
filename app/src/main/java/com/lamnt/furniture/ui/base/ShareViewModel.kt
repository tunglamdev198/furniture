package com.lamnt.furniture.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lamnt.furniture.model.dto.Production

class ShareViewModel : ViewModel() {
    var production =  MutableLiveData<Production>()
}