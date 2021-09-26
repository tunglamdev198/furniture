package com.lamnt.furniture.extensions

import androidx.lifecycle.LiveData
import com.lamnt.furniture.R
import com.lamnt.furniture.data.remote.Resource
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import com.lamnt.furniture.ui.splash.SplashActivity

/**
Handle Live Data from Remote
 */
fun <T, F : BaseFragmentMVVM<*, *>> F.handleLiveData(
    liveData: LiveData<Resource<T>>,
    onSuccess: (T?) -> Unit
) {
    liveData.observe(viewLifecycleOwner, {
        when (it.status) {
            Resource.Status.SUCCESS -> {
                hideLoading()
                onSuccess(it.data)
            }
            Resource.Status.ERROR -> {
                hideLoading()
                showInfo(it.message ?: getString(R.string.msg_has_error))
            }

            Resource.Status.LOADING -> {
                showLoading()
            }

            Resource.Status.TOKEN_EXPIRED -> {
                showAlert(
                    getString(R.string.app_name),
                    getString(R.string.msg_token_expired),
                    getString(R.string.text_login)
                ) {
                    requireActivity().navigateTo(SplashActivity::class.java, true)
                }
            }
        }
    })

}

/**
Handle Live Data from with special message
 */

fun <T, F : BaseFragmentMVVM<*, *>> F.handleLiveData(
    liveData: LiveData<Resource<T>>,
    specialMessage: String,
    onSuccess: (T?) -> Unit
) {
    liveData.observe(viewLifecycleOwner, {
        when (it.status) {
            Resource.Status.SUCCESS -> {
                hideLoading()
                onSuccess(it.data)
            }
            Resource.Status.ERROR -> {
                hideLoading()
                showInfo(specialMessage)
            }

            Resource.Status.LOADING -> {
                showLoading()
            }

            Resource.Status.TOKEN_EXPIRED -> {
                showAlert(
                    getString(R.string.app_name),
                    getString(R.string.msg_token_expired),
                    getString(R.string.text_login)
                ) {
                    requireActivity().navigateTo(SplashActivity::class.java, true)
                }
            }
        }
    })

}