package com.magic.officeapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.magic.officeapp.R
import com.magic.officeapp.ui.theme.Green100
import com.magic.officeapp.ui.theme.Red100
import com.magic.officeapp.ui.theme.Yellow100
import com.magic.officeapp.utils.constants.changeDateFormat

@Composable
fun CustomCard(
    title: String,
    created_at: String,
    icon: Int,
    Status: String = "Start",
    contentDescription: String? = "default",
    onClick: () -> Unit
) {

    val backgroundColor = when (Status) {
        "Start" -> Yellow100
        "Done" -> Green100
        else -> Red100
    }

    val Icon = when (Status) {
        "Start" -> R.drawable.request_icon
        else -> R.drawable.request_icon
    }

    val formattedDate = changeDateFormat(created_at, "yyyy-MM-dd HH:mm:ss", "dd MMMM yyyy")
    val formattedTime = changeDateFormat(created_at, "yyyy-MM-dd HH:mm:ss", "HH:mm a")


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(91.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.width(180.dp)
        ) {

            CustomIcon(
                icon = Icon,
                contentDescription = "Request",
                backgroundColor = backgroundColor,
                size = 48
            )

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = title,
                    color = Color("#292D35".toColorInt()),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = formattedDate, color = Color("#667085".toColorInt()), fontSize = 12.sp
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = formattedTime,
                color = Color("#292D35".toColorInt()),
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 12.dp),
                fontWeight = FontWeight.W600
            )
            StatusBar(label = Status, color = backgroundColor)
        }

    }

}


@Preview(showBackground = true)
@Composable
fun CustomCardPreview() {
    CustomCard(title = "Request", created_at = "2021-09-09 12:00:00", icon = 0, onClick = {})
}