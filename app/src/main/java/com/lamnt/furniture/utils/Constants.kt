package com.lamnt.furniture.utils

object Constants {
    const val URL = ""
    const val HTTP_CONTENT_TYPE_KEY = "Content-Type"
    const val HTTP_ACCEPT = "Accept"
    const val AUTHORIZATION = "Authorization"
    const val HTTP_CONTENT_TYPE_VALUE = "application/json"
    const val GOOGLE_MAP_QUERY = "http://maps.google.co.in/maps?q="
    const val CODE_401 = 401
    const val CODE_402 = 402
    const val CODE_403 = 403
    const val CODE_500 = 500
    const val NETWORK_TIME_OUT  = 60L

    // KEY ARGUMENT
    const val KEY_DATA = "DATA"
    const val KEY_URL = "URL"
    const val KEY_TYPE = "TYPE"
    const val KEY_ID = "ID"
    const val KEY_TOKEN = "TOKEN"
    const val KEY_USER = "USER"
    const val KEY_BIRTHDAY = "BIRTHDAY"
    const val KEY_KEEP_LOGIN = "KEEP_LOGIN"
    const val KEY_LOGIN_STATE = "LOGIN_STATE"
    const val KEY_CONTENT_TEXT = "CONTENT_TEXT"
    const val KEY_STATE = "STATE"
    const val KEY_TITLE = "TITLE"

    // TIME_DELAY
    const val DELAY_TIME_SPLASH: Long = 2000

    object ApiKey {
        const val EMAIL = "email"
        const val LOGIN_ID = "login_id"
        const val PASSWORD = "password"
        const val TYPE = "type"
        const val TARGET = "target"
        const val PROMOTION_ID = "promotion_id"
        const val USER_ID = "user_id"
        const val PASSWORD_NOW = "password_now"
        const val PASSWORD_NEW = "password_new"
    }

    object RequestCode {
        const val REQUEST_PERMISSION_CAMERA = 100
        const val REQUEST_PERMISSION_WRITE_STORAGE = 101
        const val REQUEST_ACCESS_FINE_LOCATION = 102
        const val REQUEST_CODE_FITNESS_PERMISSION = 103
        const val REQUEST_CODE_OPEN_LHR = 104
        const val REQUEST_CODE_ACTIVITY_RECOGNITION_PERMISSION = 105
    }
}
