package com.lamnt.furniture.data.repository.impl

import com.lamnt.furniture.data.remote.datasouce.UserDatasource

class UserRepository(private val datasource: UserDatasource) {
    fun login(username: String, password: String) = datasource.login(username, password)
}