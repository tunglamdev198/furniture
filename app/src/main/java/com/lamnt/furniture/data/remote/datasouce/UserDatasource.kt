package com.lamnt.furniture.data.remote.datasouce

import com.lamnt.furniture.data.remote.ApiService
import com.lamnt.furniture.model.dto.request.LoginRequest

class UserDatasource constructor(private val appApi: ApiService) {
    fun login(username: String, password: String) =
        appApi.login(LoginRequest(username, password, password))
}