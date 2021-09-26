package com.lamnt.furniture.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.lamnt.furniture.R
import com.lamnt.furniture.extensions.changeStatusBarColor
import com.lamnt.furniture.ui.custom.LoadingDialog

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    private lateinit var mViewDataBinding: B

    val binding: B get() = mViewDataBinding
    private var loadingDialog: LoadingDialog? = null

    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }
        loadingDialog?.show()
    }

    fun hideLoading() {
        loadingDialog?.run {
            if (isShowing) dismiss()
        }
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun onViewReady(savedInstance: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate(savedInstanceState)
        if (getLayoutId() != 0) {
            mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
            onViewReady(savedInstanceState)
        }
    }

    fun getCurrentFragment(): Fragment {
        return supportFragmentManager.findFragmentById(R.id.frameContainer)!!
    }

    override fun onResume() {
        super.onResume()
        changeStatusBarColor(R.color.white)
    }
}