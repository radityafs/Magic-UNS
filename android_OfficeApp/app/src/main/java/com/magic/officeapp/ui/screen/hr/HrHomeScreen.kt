package com.magic.officeapp.ui.screen.hr

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.data.model.response.AttendanceResponseDataItem
import com.magic.officeapp.data.model.response.announcement.DataItem
import com.magic.officeapp.data.model.response.request.GetAllRequestsDataItem
import com.magic.officeapp.ui.component.CardAttendanceHR
import com.magic.officeapp.ui.component.CustomIcon
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.theme.Green100
import com.magic.officeapp.ui.theme.Grey100
import com.magic.officeapp.ui.theme.Red100
import com.magic.officeapp.ui.viewmodel.AnnouncementViewModel
import com.magic.officeapp.ui.viewmodel.AttendanceViewModel
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.ui.viewmodel.EmployeeViewModel
import com.magic.officeapp.ui.viewmodel.RequestViewModel
import java.sql.Time
import java.text.SimpleDateFormat
import com.magic.officeapp.utils.constants.Result
import com.magic.officeapp.utils.stateAttendance

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HrHomeScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
    employeeViewModel: EmployeeViewModel = hiltViewModel(),
    requestViewModel: RequestViewModel = hiltViewModel(),
    announcementViewModel: AnnouncementViewModel = hiltViewModel(),
    attendanceViewModel: AttendanceViewModel = hiltViewModel(),
) {
    var (requestsCount, setRequestsCount) = remember {
        mutableStateOf(0)
    }
    val user = authViewModel.userData.collectAsState()

    if (user?.value?.jwt != null) {
        employeeViewModel.getEmployeeList(user.value?.jwt ?: "")
        requestViewModel.getAllRequests()
    }

    val employeeData = employeeViewModel.employeeData.collectAsState()
    val requests = requestViewModel.getAllRequestsResponse.collectAsState().value
    val currentHour = Time(System.currentTimeMillis()).hours

    var data :  List<GetAllRequestsDataItem> = emptyList()

    when (requests) {
        is Result.Success -> {
            data = requests.data.data as List<GetAllRequestsDataItem>
            setRequestsCount(data.filter { it.attributes?.isApproved == "waiting" }.size)
        }

        is Result.Error -> {
            Log.d("TAG", "AnnouncementScreen: ${requests.message}")
        }

        else -> {

        }
    }

    val announcement = announcementViewModel.announcementData.collectAsState().value
    var listAnnouncement: List<DataItem> = emptyList()
    announcementViewModel.getAllAnnouncement()

    when (announcement) {
        is Result.Success -> {
            val data = announcement.data.data
            listAnnouncement = data as List<DataItem>
        }
        is Result.Error -> {
            Log.d("TAG", "AnnouncementScreen: ${announcement.message}")
        }
        else -> {

        }
    }

    val getEmployeeResponse = employeeViewModel.getEmployeeListResponse.collectAsState().value
    val employeesData = employeeViewModel.employeeData.collectAsState().value
    val listAllAttendance = attendanceViewModel.allAttendanceData.collectAsState().value

    if (getEmployeeResponse is Result.Empty) {
        employeeViewModel.getEmployeeList("")
    }

    if (employeesData.isNotEmpty() && listAllAttendance.isEmpty()) {
        attendanceViewModel.getAllAttendance(employeesData)
    }

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
                            .padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 16.dp)
                            .clickable(onClick = {
                                navController.navigate(Screen.HrRequestScreen.route)
                            }),
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
                                text = requestsCount.toString(),
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
                            navController.navigate(Screen.HrAttendanceScreen.route)
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
                            navController.navigate(Screen.HrPayrollScreen.route)
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
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.HrAnnouncementScreen.route)
                        })
                }
            }
        }

        if(listAnnouncement.size != 0) {
            val announcement = listAnnouncement.get(0)

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = announcement.attributes?.date.toString(),
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color("#858D9D".toColorInt())
                        )


                        Text(
                            text = announcement.attributes?.title.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color("#292D35".toColorInt()),
                            modifier = Modifier.padding(top = 12.dp)
                        )


                        Text(
                            text = announcement.attributes?.description.toString(),
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color("#858D9D".toColorInt()),
                            modifier = Modifier.padding(top = 5.dp)
                        )

                        Text(
                            "To : ${announcement.attributes?.jobRoles?.data?.get(0)?.attributes?.name.toString()}",
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color("#37A345".toColorInt()),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        } else {
            item {
                Text(
                    "No Announcement",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color("#292D35".toColorInt()),
                    modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
                )
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
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.HrAttendanceScreen.route)
                        })
                }
            }
            Spacer(
                modifier = Modifier.height(18.dp)
            )
        }


        listAllAttendance.map { attendance ->
            item {
                if (attendance?.attributes?.checkOut != null) {
                    CardAttendanceHR(
                        created_at = attendance.attributes?.checkOut,
                        Status = "Check Out",
                        Name = attendance.attributes.user?.data?.attributes?.username ?: "",
                        Role = attendance.attributes.user?.data?.attributes?.jobRole?.data?.attributes?.name ?: "",
                        State = stateAttendance("Check Out", attendance.attributes.checkOut)
                    )

                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth(),
                        color = Color("#F0F1F3".toColorInt())
                    )
                } else {
                    CardAttendanceHR(
                        created_at = attendance?.attributes?.createdAt!!,
                        Name = attendance.attributes.user?.data?.attributes?.username ?: "",
                        Role = attendance.attributes.user?.data?.attributes?.jobRole?.data?.attributes?.name ?: "",
                        Status = "Check In",
                        State = stateAttendance("Check In", attendance?.attributes?.createdAt!!)
                    )

                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth(),
                        color = Color("#F0F1F3".toColorInt())
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.padding(bottom = 100.dp))
        }
    }
}


