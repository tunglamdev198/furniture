package com.lamnt.furniture.ui.main.account

import android.content.Context
import com.lamnt.furniture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(@ApplicationContext private val mContext: Context): BaseViewModel() {
}