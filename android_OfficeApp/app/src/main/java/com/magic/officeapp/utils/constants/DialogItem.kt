package com.magic.officeapp.utils.constants

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.magic.officeapp.R

data class DialogItem(
    var icon: Int = R.drawable.success_icon,
    var iconBackgroundColor: Color = Color.Transparent,
    var title: String = "Dialog Title",
    var message: String = "Dialog Message",
    var onDismissAction: (Boolean) -> Unit = {},
    var onCancelAction: (() -> Unit)? = null,
    var onConfirmAction:(Boolean) -> Unit = {},
    var backgroundButton: Color = Color("#2C803A".toColorInt()),
)
