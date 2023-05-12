package com.magic.officeapp.utils

import android.content.Context
import com.google.gson.Gson
import com.magic.officeapp.R
import java.io.InputStream

data class Holiday(
    val date: String,
    val name: String
)

fun readJsonFromRaw(context: Context, rawResourceId: Int): String {
    val inputStream: InputStream = context.resources.openRawResource(rawResourceId)
    val jsonBytes = ByteArray(inputStream.available())
    inputStream.read(jsonBytes)
    inputStream.close()

    return String(jsonBytes)
}

fun isHoliday(context: Context, date: String): Boolean {
    val holidayData = readJsonFromRaw(context, R.raw.holiday)

    val arrayHoliday = Gson().fromJson(holidayData, Array<Holiday>::class.java)

    arrayHoliday.forEach {
        if (it.date == date) {
            return true
        }
    }

    return false
}

fun getHolidayName(context: Context, date: String): String {
    val holidayData = readJsonFromRaw(context, R.raw.holiday)

    val arrayHoliday = Gson().fromJson(holidayData, Array<Holiday>::class.java)

    arrayHoliday.forEach {
        if (it.date == date) {
            return it.name
        }
    }

    return ""
}