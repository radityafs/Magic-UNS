package com.magic.officeapp.utils.constants

import androidx.compose.ui.graphics.Color


class Color {
    fun String.toColor() = Color(android.graphics.Color.parseColor(this))
}