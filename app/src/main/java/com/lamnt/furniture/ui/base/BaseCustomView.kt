package com.lamnt.furniture.ui.base

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseCustomView<VB : ViewDataBinding> : FrameLayout {
    private lateinit var mBinding: VB
    private lateinit var mTypeArray: TypedArray
    protected val binding get() = mBinding
    protected val typeArray get() = mTypeArray


    constructor(context: Context) : super(context) {
        initBinding()
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initBinding()
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initBinding()
        initView()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initView()

    private fun initBinding() {
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE).let {
            mBinding = DataBindingUtil.inflate(
                it as LayoutInflater,
                getLayoutId(),
                this,
                true
            )
        }

    }
}