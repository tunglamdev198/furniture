package com.lamnt.furniture.data.local

import com.lamnt.furniture.data.remote.datasouce.PreferenceDatasource

class PreferenceRepository(private val preferenceDatasource: PreferenceDatasource) {
    fun saveToken(token: String) = preferenceDatasource.saveToken(token)
    fun getToken() = preferenceDatasource.getToken()
}