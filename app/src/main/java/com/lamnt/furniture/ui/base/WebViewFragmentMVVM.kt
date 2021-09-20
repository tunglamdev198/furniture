package com.lamnt.furniture.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.ViewDataBinding

abstract class WebViewFragmentMVVM<V : ViewDataBinding, VM : BaseViewModel> :
    BaseFragmentMVVM<V, VM>() {
    protected abstract fun getWebView(): WebView
    protected abstract fun urlView(): String

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewReady(savedInstance: Bundle?) {
        getWebView()
        with(getWebView()) {
            settings.javaScriptEnabled = true
            isVerticalScrollBarEnabled = true
            isHorizontalScrollBarEnabled = true
            loadUrl(urlView())
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    if (activity != null) {

                    }
                }
            }
            setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK
                    && event.action == MotionEvent.ACTION_UP
                    && canGoBack()
                ) {
                    goBack()
                    return@setOnKeyListener true
                }
                false
            }
        }
    }
}