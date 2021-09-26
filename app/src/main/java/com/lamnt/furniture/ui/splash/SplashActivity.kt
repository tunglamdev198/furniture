package com.lamnt.furniture.ui.splash

import android.os.Bundle
import com.lamnt.furniture.MainActivity
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.ActivitySplashBinding
import com.lamnt.furniture.extensions.navigateTo
import com.lamnt.furniture.extensions.postDelay
import com.lamnt.furniture.ui.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun onViewReady(savedInstance: Bundle?) {
        postDelay(3000) {
            navigateTo(MainActivity::class.java, null, true);
        }
    }
}