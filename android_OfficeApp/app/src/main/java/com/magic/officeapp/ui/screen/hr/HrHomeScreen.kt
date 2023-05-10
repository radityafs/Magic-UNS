package com.magic.officeapp.ui.screen.hr

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.CustomCard
import com.magic.officeapp.ui.component.CustomIcon
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.theme.Green100
import com.magic.officeapp.ui.theme.Grey100
import com.magic.officeapp.ui.theme.Red100
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.ui.viewmodel.EmployeeViewModel
import java.sql.Time
import java.text.SimpleDateFormat
import com.magic.officeapp.utils.constants.Result

@Composable
fun HrHomeScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
    employeeViewModel: EmployeeViewModel = hiltViewModel()
) {
    val user = authViewModel.userData.collectAsState()

    if(user?.value?.jwt != null){
        employeeViewModel.getEmployeeList(user.value?.jwt ?: "")
    }

    val loading = employeeViewModel.loading.collectAsState()
    val employeeData = employeeViewModel.employeeData.collectAsState()

    val currentHour = Time(System.currentTimeMillis()).hours

    fun greeting(
        username: String = "Employee",
    ): String {
        val userName = username.split(" ")[0]
        when (currentHour) {
            in 5..11 -> {
                return "Morning, $userName \uD83E\uDD1F\uD83C\uDFFB"
            }
            in 12..15 -> {
                return "Afternoon, $userName \uD83C\uDF1E"
            }
            in 16..20 -> {
                return "Evening, $userName \uD83C\uDF1A"
            }
        }
        return "Night, $userName \uD83C\uDF19"
    }

    fun currentDate() = SimpleDateFormat("dd MMMM yyyy").format(java.util.Date())

    LazyColumn(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxSize()
    ) {
        item {
            Column {
                Row(
                    modifier = Modifier
                        .padding(top = 52.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            text = greeting(user.value?.username ?: "Employee"),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                        Text(
                            text = currentDate(), modifier = Modifier.padding(top = 10.dp)
                        )
                    }

                    Image(painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .clickable {
                                navController.navigate(Screen.ProfileScreen.route)
                            })
                }

            }
        }

        item {
            Spacer(modifier = Modifier.height(33.dp))
        }

        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Card(
                    modifier = Modifier
                        .height(108.dp)
                        .weight(1f)
                        .padding(end = 6.dp),
                    backgroundColor = Color("#292D35".toColorInt()),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Request",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 10.sp,
                                color = Color.White
                            )

                            Image(
                                painter = painterResource(id = R.drawable.back_icon_white),
                                contentDescription = "Back Icon",
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                "43",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text("Request Waiting", fontSize = 10.sp, color = Grey100)
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .height(108.dp)
                        .weight(1f)
                        .padding(start = 6.dp)
                        .clickable {
                            navController.navigate(Screen.HREmployeeListScreen.route)
                        },
                    backgroundColor = Color("#525A6A".toColorInt()),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Employee",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 10.sp,
                                color = Color.White
                            )

                            Image(
                                painter = painterResource(id = R.drawable.back_icon_white),
                                contentDescription = "Back Icon",
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                "${employeeData.value?.size}",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text("Active Employee", fontSize = 10.sp, color = Grey100)
                        }

                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp)
                    .border(1.dp, Color("#F0F1F3".toColorInt()), RoundedCornerShape(8.dp))
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.AttendanceScreen.route)
                        }) {
                        CustomIcon(
                            icon = R.drawable.attendance_icon,
                            contentDescription = "Request",
                            backgroundColor = Red100,
                            size = 48,
                        )

                        Text(
                            text = "Attendance",
                            modifier = Modifier.padding(top = 4.dp),
                            color = Color("#858D9D".toColorInt())
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.HrAnnouncementScreen.route)
                        }) {
                        CustomIcon(
                            icon = R.drawable.icon_announcement,
                            contentDescription = "Announcement",
                            backgroundColor = Color("#F2E8FF".toColorInt()),
                            size = 48,
                        )

                        Text(
                            text = "Announcement",
                            modifier = Modifier.padding(top = 4.dp),
                            color = Color("#858D9D".toColorInt())
                        )
                    }


                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.PayrollScreen.route)
                        }) {
                        CustomIcon(
                            icon = R.drawable.payroll_icon,
                            contentDescription = "Payroll",
                            backgroundColor = Green100,
                            size = 48,
                        )

                        Text(
                            text = "Payroll",
                            modifier = Modifier.padding(top = 4.dp),
                            color = Color("#858D9D".toColorInt())
                        )
                    }
                }
            }
        }
        item {

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Announcement", fontWeight = FontWeight.SemiBold, fontSize = 14.sp
                    )
                    Text(text = "See more",
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color("#858D9D".toColorInt()),
                        modifier = Modifier.clickable { })
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            "20 April 2023",
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color("#858D9D".toColorInt())
                        )


                        Text(
                            "Meeting Info",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color("#292D35".toColorInt()),
                            modifier = Modifier.padding(top = 12.dp)
                        )


                        Text(
                            "The daily meeting will be held on Monday, 16 May 2023 at 8 am at the Coworking Space",
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color("#858D9D".toColorInt()),
                            modifier = Modifier.padding(top = 5.dp)
                        )

                        Text(
                            "To : Developer, Designer",
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color("#37A345".toColorInt()),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                }
            }

        }

        item {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Recent Activity", fontWeight = FontWeight.SemiBold, fontSize = 14.sp
                    )
                    Text(text = "See more",
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color("#858D9D".toColorInt()),
                        modifier = Modifier.clickable { })
                }
            }

        }

        items(4, key = { index -> index }) { _ ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                CustomCard(title = "Request",
                    created_at = "2021-09-09 12:00:00",
                    icon = 0,
                    onClick = {})

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color = Color("#F0F1F3".toColorInt())
                )
            }
        }
        item {
            Spacer(modifier = Modifier.padding(bottom = 100.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HrHomeScreen()
}