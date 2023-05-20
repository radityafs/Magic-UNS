package com.magic.officeapp.ui.screen.hr

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.magic.officeapp.ui.component.CardAttendance
import com.magic.officeapp.ui.component.CardAttendanceHR
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.component.TextInput
import com.magic.officeapp.ui.viewmodel.AttendanceViewModel
import com.magic.officeapp.ui.viewmodel.EmployeeViewModel
import com.magic.officeapp.utils.Attendance
import com.magic.officeapp.utils.constants.Result
import com.magic.officeapp.utils.stateAttendance

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HrAttendanceListScreen(
    navController: NavController = rememberNavController(),
    attendanceViewModel: AttendanceViewModel = hiltViewModel(),
    employeeViewModel: EmployeeViewModel = hiltViewModel()
) {

    val (search, setSearch) = remember { mutableStateOf("") }
    val getEmployeeResponse = employeeViewModel.getEmployeeListResponse.collectAsState().value
    val attendanceSummary = attendanceViewModel.attendanceSummary.collectAsState().value
    val employeeData = employeeViewModel.employeeData.collectAsState().value

    val attendanceState = attendanceViewModel.attendanceState.collectAsState().value
    val listAllAttendance = attendanceViewModel.allAttendanceData.collectAsState().value
    val listAllAttendanceState = attendanceViewModel.allAttendanceState.collectAsState().value
    val listALlAttendanceSummary = attendanceViewModel.allAttendanceSummary.collectAsState().value

    if (getEmployeeResponse is Result.Empty) {
        employeeViewModel.getEmployeeList("")
    }

    if (employeeData.isNotEmpty() && listAllAttendance.isEmpty()) {
        attendanceViewModel.getAllAttendance(employeeData)
    }

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
                        text = listALlAttendanceSummary.present.toString(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#37A345".toColorInt())
                    )
                    Text(
                        text = "Present",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#858D9D".toColorInt())
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = listALlAttendanceSummary.permit.toString(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#E5B200".toColorInt())
                    )
                    Text(
                        text = "Permit",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#858D9D".toColorInt())
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = listALlAttendanceSummary.absent.toString(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#D3221E".toColorInt())
                    )
                    Text(
                        text = "Leave",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#858D9D".toColorInt())
                    )
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

            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            TextInput(
                value = search,
                onValueChange = setSearch,
                placeholder = "Search by name",
            )

            Spacer(modifier = Modifier.height(16.dp))
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
    }
}

