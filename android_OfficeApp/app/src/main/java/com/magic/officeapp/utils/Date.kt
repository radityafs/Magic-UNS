package com.magic.officeapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import itemDropdown
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun salaryMonth() : List<itemDropdown> {

    val currentDate = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale("id", "ID"))
    val salaryMonth = mutableListOf<itemDropdown>()

    for (i in 0 until 3) {
        val targetMonth = currentDate.plusMonths(i.toLong())
        val firstDayOfMonth = LocalDate.of(targetMonth.year, targetMonth.month, 1)

        val timestamp = firstDayOfMonth.atStartOfDay().toInstant(ZoneOffset.ofHours(7)).toEpochMilli()
        salaryMonth.add(itemDropdown(timestamp.toInt(), formatter.format(targetMonth)))
    }

    return salaryMonth
}