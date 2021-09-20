package com.lamnt.furniture.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.util.regex.Pattern


/**
 * Make delay in miliseconds
 */
fun postDelay(time: Long, unit: () -> Unit) {
    Handler(Looper.myLooper()!!).postDelayed({
        unit()
    }, time)
}

/**
 * Convert Int to Time format
 */

fun Int.time(): String {
    return if (this < 10) {
        "0$this"
    } else {
        "" + this
    }
}

/**
 * Check null or empty String
 */

fun String?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

fun String.isEmail(): Boolean {
    val expression = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,128}"
    val matcher = Pattern.compile(expression, Pattern.CASE_INSENSITIVE).matcher(this)
    return matcher.matches()
}

fun String.isPassword(): Boolean {
    val expression = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@\$#!%*?&]{8,}"
    val matcher = Pattern.compile(expression).matcher(this)
    return matcher.matches()
}

fun <T> T.toJson(): String = Gson().toJson(this)

fun <T> String.jsonToObject(objectClazz: Class<T>): T = Gson().fromJson(this, objectClazz)

fun <T> ArrayList<T>.copyList(): ArrayList<T> {
    val myType = object : TypeToken<List<T>>() {}.type
    return Gson().fromJson(toJson(), myType)
}

/**
 * Convert Bitmap to Uri
 */

fun Bitmap.toUri(context: Context): Uri? {
    val bytes = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(context.contentResolver, this, "Title", null)
    return Uri.parse(path)
}

/**
 * Decode Image File to Correctly Orientation Image
 */

fun File.toBitmap(): Bitmap? {
    var imageOrientation = 10
    val ei: ExifInterface
    try {
        ei = ExifInterface(absolutePath)
        imageOrientation = ei.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )
    } catch (e: IOException) {
        e.printStackTrace()
    }
    val o = BitmapFactory.Options()
    o.inJustDecodeBounds = true
    BitmapFactory.decodeFile(absolutePath, o)

    val requiredSize = 1024

    var widthTmp = o.outWidth
    var heightTmp = o.outHeight
    var scale = 1
    while (true) {
        if (widthTmp < requiredSize && heightTmp < requiredSize) break
        widthTmp /= 2
        heightTmp /= 2
        scale *= 2
    }

    val o2 = BitmapFactory.Options()
    o2.inSampleSize = scale
    val bitMap = BitmapFactory.decodeFile(absolutePath, o2)
    return when (imageOrientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitMap, 90)
        ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitMap, 180)
        ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitMap, 270)
        else -> bitMap
    }
}

fun appendHour(hour: Int, minute: Int): String {
    val builder = StringBuilder()
    if (hour < 10) {
        builder.append("0").append(hour).append(":")
    } else {
        builder.append(hour).append(":")
    }
    if (minute < 10) {
        builder.append("0").append(minute)
    } else {
        builder.append(minute)
    }
    return builder.toString()
}