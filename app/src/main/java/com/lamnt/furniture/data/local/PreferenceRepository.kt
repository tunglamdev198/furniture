package com.lamnt.furniture.data.local

import com.lamnt.furniture.data.remote.datasouce.PreferenceDatasource
import com.lamnt.furniture.model.dto.User

class PreferenceRepository(private val preferenceDatasource: PreferenceDatasource) {
    fun saveToken(token: String) = preferenceDatasource.saveToken(token)
    fun getToken() = preferenceDatasource.getToken()
    fun getUser() = preferenceDatasource.getUser()
    fun saveUser(user: User?) = preferenceDatasource.saveUser(user)
}