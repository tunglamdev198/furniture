package com.lamnt.furniture.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lamnt.furniture.MainActivity
import com.lamnt.furniture.R
import com.lamnt.furniture.extensions.changeStatusBarColor
import com.lamnt.furniture.ui.custom.LoadingDialog

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {
    private var loadingDialog: LoadingDialog? = null
    private lateinit var mViewDataBinding: B

    val binding: B get() = mViewDataBinding
    protected val shareViewModel by activityViewModels<ShareViewModel>()

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun onViewReady(savedInstance: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewReady(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        changeStatusBarColor(R.color.white)
        if (requireActivity() is MainActivity) {
            with((requireActivity() as MainActivity)) {
//                showNavigate(true)
//                showIconAccount(false)
//                showActionLeft(true)
//                showActionRight(false)
//                showButtonRight(false)
            }
        }
    }


    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(requireActivity())
        }
        loadingDialog?.show()
    }

    fun hideLoading() {
        loadingDialog?.run {
            if (isShowing) dismiss()
        }
    }

    fun isMain() = requireActivity() is MainActivity
}