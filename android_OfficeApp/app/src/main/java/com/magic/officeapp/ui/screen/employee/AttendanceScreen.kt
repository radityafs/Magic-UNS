package com.magic.officeapp.ui.screen.employee

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.component.CustomCard

@Composable
fun AttendanceScreen(
    navController: NavController = rememberNavController(),
) {
    LazyColumn(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier
                    .padding(top = 52.dp)
                    .fillMaxWidth()
                    .clickable {
                        navController.popBackStack()
                    }, verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Back Icon",
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "Attendance",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth()
                    .border(1.dp, Color("#E5E5E5".toColorInt()), RoundedCornerShape(6.dp))
                    .padding(top = 21.dp, bottom = 21.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "12",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#37A345".toColorInt())
                    )
                    Text(
                        text = "Present",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#858D9D".toColorInt())                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "5",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#E5B200".toColorInt())
                    )
                    Text(
                        text = "Absent",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#858D9D".toColorInt())                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "12",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#D3221E".toColorInt())
                    )
                    Text(
                        text = "Leave",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#858D9D".toColorInt())                    )
                }
            }
        }
        item {
            Row(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent Attendance",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )

                CustomButton(
                    modifier = Modifier
                        .height(32.dp)
                        .width(95.dp),
                    onClick = { /*TODO*/ },
                    text = "Filter",
                    IconButton = R.drawable.ic_outline_filter_list
                )
            }

        }

        items(4, key = { index -> index }) { index ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                CustomCard(
                    title = "Request",
                    created_at = "2021-09-09 12:00:00",
                    icon = 0,
                    onClick = {})

                Divider(
                    modifier = Modifier.fillMaxWidth().height(1.dp),
                    color = Color("#F0F1F3".toColorInt())
                )
            }
        }

        item{
            Spacer(modifier = Modifier.padding(bottom = 100.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AttendanceScreenPreview() {
    AttendanceScreen()
}