package com.lamnt.furniture.data.remote.datasouce

import android.content.Context

class PreferenceDatasource(context: Context) {
    private val preferences = context.getSharedPreferences("app_pref", Context.MODE_PRIVATE)
    fun saveToken(token: String) = preferences.edit().putString("token", token).apply()
    fun getToken() = preferences.getString("token", "")
}