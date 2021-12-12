package com.lamnt.furniture.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lamnt.furniture.ui.activity.MainActivity
import com.lamnt.furniture.R
import com.lamnt.furniture.extensions.navigateTo
import com.lamnt.furniture.extensions.showInfo
import com.lamnt.furniture.ui.custom.LoadingDialog

/**
 * Base Bottom Sheet Fragment with Data Binding and ViewModel
 */

abstract class BaseBottomSheetFragmentMVVM<B : ViewDataBinding, V : BaseViewModel> :
    BottomSheetDialogFragment() {
    private var loadingDialog: LoadingDialog? = null
    private lateinit var mViewDataBinding: B

    val binding: B get() = mViewDataBinding
    lateinit var viewModel: V

    /**
     * Return fragment layout id
     */

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    /**
     * Return type of ViewModel
     */

    protected abstract fun getViewModelClazz(): Class<V>

    /**
     * Run onViewCreated
     */

    protected abstract fun onViewReady(savedInstance: Bundle?)

    /**
     * Observe LiveData from ViewModel
     */

    protected abstract fun initSubscriber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
        viewModel = getViewModel(getViewModelClazz())
        observeMessage()
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
        initSubscriber()
    }

    /**
     * Handle observe loading dialog and show message
     */

    private fun observeMessage() {

        viewModel.tokenExpired.observe(this) {
            if (it) {
                logout()
            }
        }

        viewModel.message.observe(this) {
            showInfo(
                if (it.isNullOrEmpty()) {
                    "has error"
                } else {
                    it
                }
            )
        }

        viewModel.loading.observe(this) {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun logout() {
        requireActivity().applicationContext.getSharedPreferences(
            getString(R.string.preference_name),
            Context.MODE_PRIVATE
        ).edit().clear().apply()
        showInfo(getString(R.string.msg_token_expired)) {
            requireActivity().navigateTo(MainActivity::class.java, true)
        }
    }

    protected open fun <VM : BaseViewModel?> getViewModel(type: Class<VM>): VM {
        return ViewModelProvider(this, defaultViewModelProviderFactory).get(type)
    }

    private fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(requireActivity())
        }
        loadingDialog?.show()
    }

    private fun hideLoading() {
        loadingDialog?.run {
            if (isShowing) dismiss()
        }
    }
}