package com.lamnt.furniture.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.lamnt.furniture.data.remote.DataResponse
import com.lamnt.furniture.data.remote.Response
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
     * Load API from server return token, data response
     */
    protected fun <T> loadApi(
        source: Single<DataResponse<T>>,
        onSuccess: (t: T) -> Unit,
        returnToken: (token: String) -> Unit
    ) {
        addDispose(
            source.doOnSubscribe {
                loading.postValue(true)
            }.doOnTerminate {
                loading.postValue(false)
            }.io().subscribe({
                it?.let { response ->
                    response.token?.run {
                        returnToken(this)
                    }
                    handleResponse(response, onSuccess, {})
                }
            }, { throwable ->
                handleNetworkError(throwable)
            })
        )
    }

//    /**
//     * Load API from server return page, data response
//     */
//    protected fun <T> loadApiPaging(
//        source: Single<DataResponse<T>>,
//        onSuccess: (t: T) -> Unit,
//        returnPage: (allPage: Int) -> Unit
//    ) {
//        addDispose(
//            source.doOnSubscribe {
//                loading.postValue(true)
//            }.doOnTerminate {
//                loading.postValue(false)
//            }.io().subscribe({
//                it?.let { response ->
//                    response.allCount?.run {
//                        returnPage(this)
//                    }
//                    handleResponse(response, onSuccess, {})
//                }
//            }, { throwable ->
//                handleNetworkError(throwable)
//            })
//        )
//    }

    /**
     * Load API from server
     */
    protected fun <T> loadApi(
        source: Single<DataResponse<T>>,
        onSuccess: (t: T) -> Unit
    ) {
        addDispose(
            source.doOnSubscribe {
                loading.postValue(true)
            }.doOnTerminate {
                loading.postValue(false)
            }.io().subscribe({
                it?.let { response ->
                    handleResponse(response, onSuccess, {})
                }
            }, { throwable ->
                handleNetworkError(throwable)
            })
        )
    }

    /**
     * Load API from server return data response
     */
    protected fun <T> loadApi(
        source: Single<DataResponse<T>>,
        onSuccess: (t: T) -> Unit,
        onError: () -> Unit
    ) {
        addDispose(
            source.doOnSubscribe {
                loading.postValue(true)
            }.doOnTerminate {
                loading.postValue(false)
            }.io().subscribe({
                it?.let { response ->
                    handleResponse(response, onSuccess, onError)
                }
            }, { throwable ->
                onError()
                handleNetworkError(throwable)
            })
        )
    }

    /**
     * Load API from server
     */
    protected fun loadApi(
        source: Single<Response>,
        onSuccess: () -> Unit
    ) {
        addDispose(
            source.doOnSubscribe {
                loading.postValue(true)
            }.doOnTerminate {
                loading.postValue(false)
            }.io().subscribe({
                if (it.status == 0) {
                    onSuccess()
                } else {
                    it.message?.run {
                        setMessage(this)
                    }
                }
            }, { throwable ->
                handleNetworkError(throwable)
            })
        )
    }

    /**
     * Load API from server
     */
    protected fun loadApi(
        source: Single<Response>,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        addDispose(
            source.doOnSubscribe {
                loading.postValue(true)
            }.doOnTerminate {
                loading.postValue(false)
            }.io().subscribe({
                if (it.status == 0) {
                    onSuccess()
                } else {
                    onError()
                    it.message?.run {
                        setMessage(this)
                    }
                }
            }, { throwable ->
                onError()
                handleNetworkError(throwable)
            })
        )
    }

    /**
     * Load API from server return data response, not handle error
     */
    protected fun <T> loadApiV2(
        source: Single<DataResponse<T>>,
        onSuccess: (t: T) -> Unit,
        onError: () -> Unit
    ) {
        addDispose(
            source.doOnSubscribe {
                loading.postValue(true)
            }.doOnTerminate {
                loading.postValue(false)
            }.io().subscribe({
                it?.let { response ->
                    handleResponseNoMessage(response, onSuccess, onError)
                } ?: run {
                    onError()
                }
            }, {
                handleErrorNoMessage(it, onError)
            })
        )
    }

    /**
     * Load API from server return data response, not handle error, return token login
     */
    protected fun <T> loadApiV2(
        source: Single<DataResponse<T>>,
        onSuccess: (t: T) -> Unit,
        returnToken: (token: String) -> Unit,
        onError: () -> Unit
    ) {
        addDispose(
            source.doOnSubscribe {
                loading.postValue(true)
            }.doOnTerminate {
                loading.postValue(false)
            }.io().subscribe({
                it?.let { response ->
                    response.token?.run {
                        returnToken(this)
                    }
                    handleResponseNoMessage(response, onSuccess, onError)
                } ?: run {
                    onError()
                }
            }, {
                handleErrorNoMessage(it, onError)
            })
        )
    }

//    /**
//     * Load API from server return data response, not handle error
//     */
//    protected fun <T> loadApiPagingV2(
//        source: Single<DataResponse<T>>,
//        onSuccess: (t: T) -> Unit,
//        returnPage: (allPage: Int) -> Unit,
//        onError: () -> Unit
//    ) {
//        addDispose(
//            source.doOnSubscribe {
//                loading.postValue(true)
//            }.doOnTerminate {
//                loading.postValue(false)
//            }.io().subscribe({
//                it?.let { response ->
//                    response.allCount?.run {
//                        returnPage(this)
//                    }
//                    handleResponseNoMessage(response, onSuccess, onError)
//                } ?: run {
//                    onError()
//                }
//            }, {
//                handleErrorNoMessage(it, onError)
//            })
//        )
//    }

    /**
     * handle error request on throwable as Failure
     * @param throwable wrap with failure
     * @return failure throwable
     */

    protected fun handleNetworkError(throwable: Throwable?) {
        if (throwable is HttpException) {
            if (throwable.code() == Constants.CODE_401
                || throwable.code() == Constants.CODE_402
                || throwable.code() == Constants.CODE_403
            ) {
                if (tokenExpired.value != true) {
                    tokenExpired.value = true
                }
            } else if (throwable.code() == Constants.CODE_500) {
                message.postValue("処理中にエラーが発生しました。")
            } else {
                throwable.response()?.errorBody()?.let {
                    val adapter: TypeAdapter<Error> =
                        Gson().getAdapter(Error::class.java)
                    try {
                        val error: Error? = adapter.fromJson(it.string())
                        if (error != null) {
                            message.postValue(error.message)
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                        message.postValue(e.message)
                    }
                } ?: run {
                    message.postValue("処理中にエラーが発生しました。")
                }
            }
        } else {
            message.postValue(throwable?.message ?: "処理中にエラーが発生しました。")
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
        onSuccess: (t: T) -> Unit,
        onError: () -> Unit
    ) {
        if (response.status == 0) {
            response.data?.let { data ->
                onSuccess(data)
            }
        } else {
            onError()
            response.message?.let {
                setMessage(it)
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
        if (response.status == 0) {
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
