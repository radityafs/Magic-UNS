package com.magic.officeapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.magic.officeapp.R
import com.magic.officeapp.ui.theme.Green100
import com.magic.officeapp.ui.theme.Red100
import com.magic.officeapp.ui.theme.Yellow100

@Composable
fun Menu(
//    navController: NavController
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable {

            }
        ) {
            CustomIcon(
                icon = R.drawable.request_icon,
                contentDescription = "Request",
                backgroundColor = Yellow100,
                size = 48,
            )

            Text(text = "Request", modifier = Modifier.padding(top = 4.dp), color = Color("#667085".toColorInt()))
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable {

            }
        ) {
            CustomIcon(
                icon = R.drawable.attendance_icon,
                contentDescription = "Request",
                backgroundColor = Red100,
                size = 48,
            )

            Text(text = "Attendance", modifier = Modifier.padding(top = 4.dp), color = Color("#667085".toColorInt()))
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable {

            }
        ) {
            CustomIcon(
                icon = R.drawable.payroll_icon,
                contentDescription = "Request",
                backgroundColor = Green100,
                size = 48,
            )

            Text(text = "Payroll", modifier = Modifier.padding(top = 4.dp), color = Color("#667085".toColorInt()))
        }


    }
}

@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    Menu()
}