package com.magic.officeapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.magic.officeapp.data.model.response.AttendanceResponseDataItem
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
fun stateAttendance(Status: String, Datetime: String): String {
    val utcFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val utcDateTime = LocalDateTime.parse(Datetime, utcFormatter)
    val wibZoneId = ZoneId.of("Asia/Jakarta")
    val wibDateTime = utcDateTime.atOffset(ZoneOffset.UTC).atZoneSameInstant(wibZoneId)

    val hour = wibDateTime.hour

    if (Status == "Check In") {
        return when (hour) {
            in 0..9 -> {
                "On Time"
            }
            else -> {
                "Late"
            }
        }
    } else {
        return when (hour) {
            in 9..15 -> {
                "Early"
            }
            in 16..17 -> {
                "On Time"
            }
            else -> {
                "Overtime"
            }
        }
    }
}

data class Attendance(
    val present: Int,
    val absent: Int,
    val late: Int,
    val permit : Int,
    val early: Int,
    val overtime: Int
)

@RequiresApi(Build.VERSION_CODES.O)
fun countDatesUntilNow(startDate: String): Int {
    val utcFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    val utcStartDate = LocalDateTime.parse(startDate, utcFormatter).toLocalDate()
    val currentDate = LocalDate.now()

    return ChronoUnit.DAYS.between(utcStartDate, currentDate).toInt()
}

@RequiresApi(Build.VERSION_CODES.O)
fun countHolidays(startDate: String, endDate: String): Int {
    val utcFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    val utcStartDate = LocalDateTime.parse(startDate, utcFormatter).toLocalDate()
    val utcEndDate = LocalDateTime.parse(endDate, utcFormatter).toLocalDate()

    // get list of dates between startDate and endDate
    val dates = mutableListOf<LocalDate>()
    var date = utcStartDate
    while (date.isBefore(utcEndDate)) {
        dates.add(date)
        date = date.plusDays(1)
    }

    // count holidays
    var holidays = 0
    dates.forEach {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateString = it.format(dateFormat)

        if (it.dayOfWeek.value == 6 || it.dayOfWeek.value == 7 || isHoliday(dateString)) {
            holidays++
        }
    }

    return holidays
}

@RequiresApi(Build.VERSION_CODES.O)
fun attendanceSummary(attendanceList: List<AttendanceResponseDataItem>): Attendance {
    val firstItem = attendanceList.first()
    val lastItem = attendanceList.last()
    val totalDays = countDatesUntilNow(lastItem?.attributes?.createdAt!!)

    val totalWorkDays = totalDays - countHolidays(
        firstItem?.attributes?.createdAt!!,
        lastItem?.attributes?.createdAt!!
    )
//
    val totalAttendance = attendanceList.size

//    var present = totalAttendance
//    var absent = totalWorkDays - totalAttendance
//    var late = 0
//    var early = 0
//    var overtime = 0
//
//    attendanceList.forEach {
//        val checkIn = it.attributes?.publishedAt
//        val checkOut = it.attributes?.checkOut
//
//        val checkInState = stateAttendance("Check In", checkIn!!)
//        val checkOutState = stateAttendance("Check Out", checkOut!!)
//
//        if (checkInState == "Early" && checkOutState == "On Time") {
//            early++
//        } else if (checkInState == "On Time" && checkOutState == "Overtime") {
//            overtime++
//        } else {
//            late++
//        }
//
//    }

    return Attendance(
        present = totalAttendance,
        absent = totalWorkDays - totalAttendance,
        late = 0,
        permit = 0,
        early = 0,
        overtime = 0
    )

}