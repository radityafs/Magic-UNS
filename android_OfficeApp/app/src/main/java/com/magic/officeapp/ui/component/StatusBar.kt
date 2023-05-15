package com.magic.officeapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.magic.officeapp.ui.theme.*

@Composable
fun StatusBar(
    label: String,
    color: Color
) {

   val contentColor = when(color){
        Yellow100 -> Yellow500
        Green100 -> Green500
        else -> Red500
    }

    Row(
        modifier = Modifier
            .width(80.dp)
            .height(28.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(6.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = label.capitalize(), color = contentColor, fontSize = 11.sp, letterSpacing = 0.5.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun StatusBarPreview() {
    StatusBar(
        label = "Success", color = Green100
    )
}