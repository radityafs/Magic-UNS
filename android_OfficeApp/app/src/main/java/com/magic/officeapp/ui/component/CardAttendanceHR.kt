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
import androidx.compose.ui.tooling.preview.Preview
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
fun CardAttendanceHR(
    created_at: String = "2021-08-12T00:00:00.000Z",
    Status: String = "Start",
    Name : String = "John Doe",
    State : String = "On Time",
    Role : String = "HR",
    onClick : () -> Unit = {}
) {

    val backgroundColor = when (Status) {
        "Check In" -> Green100
        else -> Red100
    }

    val icon = when (Status) {
        "Check In" -> R.drawable.icon_leave_green
        else -> R.drawable.attendance_icon
    }

    val backgroundStatus = when(State){
        "On Time" -> Green100
        "Late" -> Red100
        else -> Yellow100
    }


    val formattedDate = utcToFormat(created_at, "dd MMMM yyyy HH:mm a")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(91.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.width(200.dp)
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
                    text = Name,
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
                text = Role,
                color = Color("#292D35".toColorInt()),
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 12.dp),
                fontWeight = FontWeight.W600
            )
            StatusBar(label = State, color = backgroundStatus)
        }

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CardAttendanceHRPreview() {
    CardAttendanceHR()
}


