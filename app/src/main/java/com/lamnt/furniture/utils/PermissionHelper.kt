package com.lamnt.furniture.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionHelper {
    companion object {
        fun checkPermissions(activity: Activity?, permission: String): Boolean {
            return ContextCompat.checkSelfPermission(
                activity!!,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }

        fun requestPermissions(activity: Activity?, permission: String, code: Int) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(permission),
                code
            )
        }

        fun requestPermissions(activity: Activity?, permission: Array<String>, code: Int) {
            ActivityCompat.requestPermissions(
                activity!!,
                permission,
                code
            )
        }
    }
}