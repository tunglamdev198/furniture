package com.lamnt.furniture.utils

import android.annotation.SuppressLint
import com.lamnt.furniture.extensions.time
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateTimeUtils {
    fun getListDay(): List<String> {
        val days: MutableList<String> = ArrayList()
        for (i in 1..31) {
            days.add(i.time() + "日")
        }
        return days
    }

    fun getListMonth(): List<String> {
        val months: MutableList<String> = ArrayList()
        for (i in 1..12) {
            months.add(i.time() + "月")
        }
        return months
    }

    fun getListYear(): List<String> {
        val year: MutableList<String> = ArrayList()
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val currentYear = calendar[Calendar.YEAR]
        val startYear = 1900
        for (i in startYear..currentYear) {
            year.add(i.time() + "年")
        }
        return year
    }

    fun getListHour(): List<String> {
        val hours: MutableList<String> = ArrayList()
        for (i in 0..23) {
            hours.add(i.time())
        }
        return hours
    }

    fun getListMinute(): List<String> {
        val minutes: MutableList<String> = ArrayList()
        for (i in 0..59) {
            minutes.add(i.time())
        }
        return minutes
    }

    fun getNumberDayOfMonth(month: Int, year: Int): Int {
        val calendar = Calendar.getInstance()
        calendar[year, month - 1] = 1
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    @SuppressLint("SimpleDateFormat")
    fun calculateDate(startDate: String, endDate: String): Long {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val dateStart = formatter.parse(startDate)
        val dateEnd = formatter.parse(startDate)
        return if (dateStart != null && dateEnd != null) {
            TimeUnit.DAYS.convert(dateEnd.time - dateStart.time, TimeUnit.MILLISECONDS)
        } else 0L
    }

    @SuppressLint("SimpleDateFormat")
    fun calculateWithNow(date: String): Long {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val dateObj = formatter.parse(date)
        val now = Calendar.getInstance()
        now.set(Calendar.HOUR_OF_DAY, 0)
        now.set(Calendar.MINUTE, 0)
        now.set(Calendar.SECOND, 0)
        return if (dateObj != null) {
            TimeUnit.DAYS.convert(dateObj.time - now.time.time, TimeUnit.MILLISECONDS)
        } else 0L
    }

    fun convertDateFromService(date: String?, newFormat: String): String {
        return if (!date.isNullOrEmpty()) {
            var formatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.JAPAN)
            val dateObj = formatter.parse(date)
            formatter = SimpleDateFormat(newFormat, Locale.JAPAN)
            dateObj?.let {
                formatter.format(dateObj)
            } ?: ""
        } else {
            ""
        }
    }

    fun getTimeTodayKenko(): String {
        val formatter = SimpleDateFormat("yyyy/MM/dd")
        val day = formatter.format(Calendar.getInstance().time)
        return "$day(${getNameOfDay(Calendar.getInstance().time)})"
    }

    @SuppressLint("SimpleDateFormat")
    fun getTimeByFormat(pattern: String, date: Date): String {
        val formatter = SimpleDateFormat(pattern)
        return formatter.format(date)

    }

    private fun getNameOfDay(date: Date): String {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val daysArray =
            arrayOf("日", "月", "火", "水", "木", "金", "土")
        return daysArray[calendar.get(Calendar.DAY_OF_WEEK) - 1]
    }

    fun getNameOfDay(date: String, format: String): String {
        val day = convertStringToDate(date, format)
        return getNameOfDay(day)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDateToString(date: Date, pattern: String): String {
        val formatter = SimpleDateFormat(pattern)
        return formatter.format(date) ?: ""
    }

    @SuppressLint("SimpleDateFormat")
    fun convertStringToDate(date: String, pattern: String): Date {
        val formatter = SimpleDateFormat(pattern)
        return formatter.parse(date)!!
    }

    fun getTimestamp(date: String, pattern: String): Long {
        return convertStringToDate(date, pattern).time
    }

    @SuppressLint("SimpleDateFormat")
    fun convertTimestampToDate(timeStamp: Long, pattern: String): String {
        val date = Date(timeStamp)
        val formatter = SimpleDateFormat(pattern)
        return formatter.format(date) ?: ""
    }

    fun getDayBefore(startTime: String, rangeTime: Int, timeUnit: Int): Long {
        val currentDate = convertStringToDate(startTime, "yyyy/MM/dd")
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        calendar.add(timeUnit, -rangeTime)
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        return calendar.time.time
    }

    fun getTimeForRequest(timeStamp: Long): String {
        return getTimeForRequest(Date(timeStamp)) ?: ""
    }

    private fun getTimeForRequest(date: Date): String? {
        @SuppressLint("SimpleDateFormat")
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        return try {
            sdf.format(date)
        } catch (e: Exception) {
            ""
        }
    }

    fun isToday(date: String): String {
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        val today = sdf.format(Date())
        return if (today == date) {
            "今日"
        } else {
            date
        }
    }

    fun checkToday(date: String): Boolean {
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        val today = sdf.format(Date())
        return today == date
    }

    fun calculateTwoTime(startTime: Long, endTime: Long): String {
        val diff = endTime - startTime
        val diffMinutes = diff / (60 * 1000)
        val diffHours = diff / (60 * 60 * 1000)
        val time = StringBuilder()
        if (diffHours < 10) {
            time.append("0").append(diffHours).append(":")
        } else {
            time.append(diffHours).append(":")
        }
        val minute = diffMinutes - 60 * diffHours
        if (minute < 10) {
            time.append("0").append(minute)
        } else {
            time.append(minute)
        }
        return time.toString()
    }

    fun calculateTwoTimeValue(startTime: Long, endTime: Long): Float {
        val diff = endTime - startTime
        val diffMinutes = diff / (60 * 1000)
        val diffHours = diff / (60 * 60 * 1000)
        val minute = diffMinutes - 60 * diffHours
        return diffHours + minute.toFloat() / 60
    }

    fun checkSleepDate(
        startDate: String,
        startTime: String,
        endDate: String,
        endTime: String
    ): Long {
        val start = convertStringToDate("$startDate $startTime", "yyyy/MM/dd HH:mm").time
        val end = convertStringToDate("$endDate $endTime", "yyyy/MM/dd HH:mm").time
        return end - start
    }

}