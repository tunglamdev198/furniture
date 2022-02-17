package com.lamnt.furniture.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.lamnt.furniture.data.remote.DataResponse
import com.lamnt.furniture.data.remote.Resource
import com.lamnt.furniture.extensions.io
import com.lamnt.furniture.utils.Constants
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.HttpException
import java.io.IOException

open class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val message by lazy { MutableLiveData<String>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    val tokenExpired by lazy { MutableLiveData<Boolean>() }

    protected fun addDispose(d: Disposable) {
        compositeDisposable.add(d)
    }

    fun setMessage(message: String) {
        this.message.value = message
    }

    /**
     * clear all subscription and dispose
     */
    private fun dispose() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

    /**
     * Load API from server
     */
    protected fun <T> loadApi(
        source: Single<DataResponse<T>>,
        onResult: (resource: Resource<T>) -> Unit
    ) {
        addDispose(
            source.doOnSubscribe {
                onResult(Resource.loading(null))
            }.io().subscribe({
                handleResponse(it, onResult)
            }, { throwable ->
                handleNetworkError(throwable){
                    onResult(Resource.error(it ?: "Has error"))
                }
            })
        )
    }

    /**
     * handle error request on throwable as Failure
     * @param throwable wrap with failure
     * @return failure throwable
     */

    protected fun handleNetworkError(throwable: Throwable?, onError: (message : String?) -> Unit) {
        if (throwable is HttpException) {
            if (throwable.code() == Constants.CODE_401
                || throwable.code() == Constants.CODE_402
                || throwable.code() == Constants.CODE_403
            ) {
                if (tokenExpired.value != true) {
                    tokenExpired.value = true
                }
            } else if (throwable.code() == Constants.CODE_500) {
                onError("Error")
            } else {
                throwable.response()?.errorBody()?.let {
                    val adapter: TypeAdapter<Error> =
                        Gson().getAdapter(Error::class.java)
                    try {
                        val error: Error? = adapter.fromJson(it.string())
                        if (error != null) {
                            onError(error.message)
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                        onError(e.message)
                    }
                } ?: run {
                    onError("Error")
                }
            }
        } else {
            onError(throwable?.message ?: "Error")
        }
    }

    /**
     * handle error request on throwable as Failure not show message
     * @param throwable wrap with failure
     * @return failure throwable
     */

    private fun handleErrorNoMessage(throwable: Throwable?, onError: () -> Unit) {
        if (throwable is HttpException) {
            if (throwable.code() == Constants.CODE_401
                || throwable.code() == Constants.CODE_402
                || throwable.code() == Constants.CODE_403
            ) {
                if (tokenExpired.value != true) {
                    tokenExpired.value = true
                }
            } else {
                onError()
            }
        } else {
            onError()
        }
    }

    /**
     * Handle data response
     */

    private fun <T> handleResponse(
        response: DataResponse<T>,
        onResult: (resource: Resource<T>) -> Unit,
    ) {
        if (response.status == "0") {
            response.data?.let { data ->
                onResult(Resource.success(data))
            }
        } else {
            response.message?.let {
                onResult(Resource.error(it, null))
            }
        }

    }

    /**
     * Handle data response not show Message
     */

    private fun <T> handleResponseNoMessage(
        response: DataResponse<T>,
        onSuccess: (t: T) -> Unit,
        onError: () -> Unit
    ) {
        if (response.status == "0") {
            response.data?.let { data ->
                onSuccess(data)
            } ?: run {
                onError()
            }
        } else {
            onError()
        }

    }

    /**
     * dispose all subscription when lifecycle onDestroy
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onCleared() {
        dispose()
        super.onCleared()
    }
}
