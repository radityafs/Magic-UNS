package com.magic.officeapp.ui.screen.employee

import android.os.Build
import android.util.Log
import android.widget.Toast
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
import com.magic.officeapp.data.model.response.AttendanceResponseDataItem
import com.magic.officeapp.ui.component.CardAttendance
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.viewmodel.AttendanceViewModel
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.utils.Attendance
import com.magic.officeapp.utils.attendanceSummary
import com.magic.officeapp.utils.constants.Result
import com.magic.officeapp.utils.stateAttendance
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AttendanceScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
    attendanceViewModel: AttendanceViewModel = hiltViewModel()
) {
    val user = authViewModel.userData.collectAsState().value
    var attendanceList = emptyList<AttendanceResponseDataItem>()
    val attendanceSummary = attendanceViewModel.attendanceSummary.collectAsState().value

    val attendanceState = attendanceViewModel.attendanceState.collectAsState().value

    if (user?.jwt != null) {
        attendanceViewModel.getAttendanceUser(user.id.toString())
    }

    when (attendanceState) {
        is Result.Success -> {
            attendanceList = attendanceState?.data?.data as List<AttendanceResponseDataItem>
        }
        is Result.Error -> {
            Toast.makeText(navController.context, attendanceState.message, Toast.LENGTH_SHORT)
                .show()
        }
        else -> {
        }
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
                        text = attendanceSummary?.present.toString(),
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
                        text = attendanceSummary?.permit.toString(),
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
                        text = attendanceSummary?.absent.toString(),
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

        }

        attendanceList.map { attendance ->
            item {
                if (attendance?.attributes?.checkOut != null) {
                    CardAttendance(
                        created_at = attendance.attributes.checkOut,
                        Status = "Check Out",
                        State = stateAttendance("Check Out", attendance.attributes.checkOut),
                        onClick = { /*TODO*/ }
                    )

                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth(),
                        color = Color("#F0F1F3".toColorInt())
                    )
                }

                CardAttendance(
                    created_at = attendance?.attributes?.createdAt!!,
                    Status = "Check In",
                    State = stateAttendance("Check In", attendance?.attributes?.createdAt!!),
                    onClick = { /*TODO*/ }
                )

                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth(),
                    color = Color("#F0F1F3".toColorInt())
                )
            }
        }

        item {
            Spacer(modifier = Modifier.padding(bottom = 100.dp))
        }
    }
}

