package com.magic.officeapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import itemDropdown
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun salaryMonth(): List<itemDropdown> {
    val currentDate = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale("id", "ID"))
    val salaryMonth = mutableListOf<itemDropdown>()

    for (i in 0 until 3) {
        val targetMonth = currentDate.plusMonths(i.toLong())
        val firstDayOfMonth = LocalDate.of(targetMonth.year, targetMonth.month, 1)

        val timestamp =
            firstDayOfMonth.atStartOfDay().toInstant(ZoneOffset.ofHours(7)).toEpochMilli()
        salaryMonth.add(itemDropdown(timestamp.toInt(), formatter.format(targetMonth)))
    }

    return salaryMonth
}

data class MonthStartEndData(
    val start: String,
    val end: String
)

@RequiresApi(Build.VERSION_CODES.O)
fun monthStartEnd(monthYear: String): MonthStartEndData {
    // Mei 2021
    val formatter = SimpleDateFormat("MMMM yyyy", Locale("id", "ID"))
    val date = formatter.parse(monthYear)

    val firstDayOfMonth = LocalDate.of(
        date.year + 1900,
        date.month + 1,
        1
    ).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    // get last day of month, in date format
    val lastDayOfMonth = LocalDate.of(
        date.year + 1900,
        date.month + 1,
        YearMonth.of(date.year + 1900, date.month + 1).lengthOfMonth()
    ).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    return MonthStartEndData(firstDayOfMonth, lastDayOfMonth)
}

@RequiresApi(Build.VERSION_CODES.O)
fun dateToUTC(date: String): String {
    val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    val localDate = LocalDate.parse(date, inputFormat)
    val localDateTime = LocalDateTime.of(localDate, LocalTime.MIDNIGHT)

    val zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Asia/Jakarta"))
    val utcDateTime = zonedDateTime.withZoneSameInstant(ZoneOffset.UTC)

    return utcDateTime.format(outputFormat).toString()
}