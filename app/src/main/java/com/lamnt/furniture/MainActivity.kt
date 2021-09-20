package com.lamnt.furniture

import android.os.Bundle
import com.lamnt.furniture.databinding.ActivityMainBinding
import com.lamnt.furniture.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onViewReady(savedInstance: Bundle?) {

    }
}