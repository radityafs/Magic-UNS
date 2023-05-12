package com.magic.officeapp.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.magic.officeapp.R
import com.magic.officeapp.ui.theme.Green100
import com.magic.officeapp.ui.theme.Red100
import com.magic.officeapp.ui.theme.Yellow100
import com.magic.officeapp.utils.constants.utcToFormat

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardRequests(
    title: String,
    created_at: String = "2021-08-12T00:00:00.000Z",
    requestType: String = "permit",
    Status: String = "Start",
    onClick : () -> Unit = {}
) {

    val backgroundColor = when (Status) {
        "waiting" -> Yellow100
        "approved" -> Green100
        else -> Red100
    }

    val icon = when (requestType) {
        "permit" -> R.drawable.request_icon
        else -> R.drawable.icon_document
    }

    val formattedDate = utcToFormat(created_at, "dd MMMM yyyy")
    val formattedTime = utcToFormat(created_at, "HH:mm a")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(91.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.width(180.dp)
        ) {

            CustomIcon(
                icon = icon,
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


