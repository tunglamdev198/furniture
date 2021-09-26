package com.lamnt.furniture.data.remote.datasouce

import com.lamnt.furniture.data.remote.Resource
import com.lamnt.furniture.utils.Constants
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return handleErrorNoMessage(response)
        } catch (e: Exception) {
            return Resource.error(e.message!!, null)
        }
    }

    private fun <T> handleErrorNoMessage(response: Response<T>): Resource<T> {
        return if (response.code() == Constants.CODE_401
            || response.code() == Constants.CODE_402
            || response.code() == Constants.CODE_403
        ) {
            Resource.reLogin(null)
        } else {
            Resource.error(response.message(), null)
        }
    }

}