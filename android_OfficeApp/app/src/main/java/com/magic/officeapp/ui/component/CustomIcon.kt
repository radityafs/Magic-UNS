package com.magic.officeapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.magic.officeapp.R
import com.magic.officeapp.ui.theme.Yellow100
import com.magic.officeapp.ui.theme.Yellow600

@Composable
fun CustomIcon(
    size: Int = 24,
    icon: Int,
    contentDescription: String? = "default",
    backgroundColor: Color = Color.Transparent,
) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(50))
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = contentDescription,
            modifier = Modifier
                .padding(8.dp)
                .size(size.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomIconPreview() {
    CustomIcon(
        size = 48,
        icon = R.drawable.request_icon,
        backgroundColor = Yellow100,
    )
}