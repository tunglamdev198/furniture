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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.lamnt.furniture.MainActivity
import com.lamnt.furniture.R
import com.lamnt.furniture.extensions.*

/**
 * Base Fragment with Data Binding and ViewModel
 */

abstract class BaseFragmentMVVM<B : ViewDataBinding, V : BaseViewModel> : Fragment() {

    private lateinit var mViewDataBinding: B

    val binding: B get() = mViewDataBinding
    lateinit var viewModel: V
    protected val shareViewModel by activityViewModels<ShareViewModel>()

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
        viewModel = getViewModel(getViewModelClazz())
        observeMessage()
        setHasOptionsMenu(true)
        changeStatusBarColor(R.color.white)
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

    @SuppressLint("CommitPrefEdits")
    private fun observeMessage() {
        viewModel.message.observe(this) {
            showInfo(
                if (it.isNullOrEmpty()) {
                    getString(R.string.msg_has_error)
                } else {
                    it
                }
            )
        }

        viewModel.tokenExpired.observe(this) {
            if (it) {
                logout()
            }
        }

        viewModel.loading.observe(this) {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }
    }

    fun showLoading() {
        (requireActivity() as BaseActivity<*>).showLoading()
    }

    fun hideLoading() {
        (requireActivity() as BaseActivity<*>).hideLoading()
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

    fun isMain() = requireActivity() is MainActivity

    override fun onResume() {
        super.onResume()
        if (isMain()) {
            with((requireActivity() as MainActivity)) {
                showNavigate(true)
                showButtonBack(true)
                showBottomBar(false)
                changeStatusBarColor(R.color.white)
            }
        }
    }
}