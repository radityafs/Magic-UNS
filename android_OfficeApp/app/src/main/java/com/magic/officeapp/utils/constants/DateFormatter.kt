package com.magic.officeapp.utils.constants

import java.text.SimpleDateFormat

fun changeDateFormat(
    date: String,
    currentFormat: String,
    requiredFormat: String
): String {
    val parser = SimpleDateFormat(currentFormat)
    val formatter = SimpleDateFormat(requiredFormat)
    return formatter.format(parser.parse(date)!!)
}
