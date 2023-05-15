package com.magic.officeapp.utils.constants

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*



@RequiresApi(Build.VERSION_CODES.O)
fun utcToFormat(
    utcString: String?,
    targetPattern: String
): String {
    val utcFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val wibFormatter = DateTimeFormatter.ofPattern(targetPattern, Locale("id", "ID"))

    val utcDateTime = LocalDateTime.parse(utcString ?: "2023-05-11T15:54:21.058Z", utcFormatter)
    val wibZoneId = ZoneId.of("Asia/Jakarta")
    val wibDateTime = utcDateTime.atOffset(ZoneOffset.UTC).atZoneSameInstant(wibZoneId)

    return wibDateTime.format(wibFormatter)
}
