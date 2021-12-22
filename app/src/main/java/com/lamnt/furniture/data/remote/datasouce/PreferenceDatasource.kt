package com.lamnt.furniture.data.remote.datasouce

import android.content.Context
import com.google.gson.Gson
import com.lamnt.furniture.model.dto.User

class PreferenceDatasource(private val gson: Gson, context: Context) {
    private val preferences = context.getSharedPreferences("app_pref", Context.MODE_PRIVATE)
    fun saveToken(token: String) = preferences.edit().putString("token", token).apply()
    fun getToken() = preferences.getString("token", "")
    fun getUser(): User? = gson.fromJson(preferences.getString("user", ""), User::class.java)
    fun saveUser(user: User?) =
        preferences.edit().putString("user", user?.let { gson.toJson(user) ?: "" }).apply()
}