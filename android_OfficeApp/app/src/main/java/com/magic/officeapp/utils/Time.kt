package com.magic.officeapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun timeToText(time: String) : String {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    format.timeZone = TimeZone.getTimeZone("UTC")
    val date = format.parse(time)
    val dateTime = date.toInstant().toEpochMilli()

    val now = System.currentTimeMillis()

    val ago = now - dateTime

    val seconds = ago / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val weeks = days / 7

    when {
        weeks > 0 -> {
            return "$weeks weeks ago"
        }
        days > 0 -> {
            return "$days days ago"
        }
        hours > 0 -> {
            return "$hours hours ago"
        }
        minutes > 0 -> {
            return "$minutes minutes ago"
        }
        seconds > 0 -> {
            return "$seconds seconds ago"
        }
        else -> {
            return "Just now"
        }
    }

}