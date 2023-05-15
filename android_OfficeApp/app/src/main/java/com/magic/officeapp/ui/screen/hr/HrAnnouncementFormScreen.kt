package com.magic.officeapp.ui.screen.hr

import Dropdown
import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.TextInput
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.magic.officeapp.data.model.LoginResponse
import com.magic.officeapp.data.model.response.AddAnnouncementResponse
import com.magic.officeapp.data.model.response.JobRolesResponse
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.viewmodel.AnnouncementViewModel
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.ui.viewmodel.EmployeeViewModel
import com.magic.officeapp.utils.constants.Result
import itemDropdown
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

@Composable
fun HrAnnouncementFormScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
    announcementViewModel: AnnouncementViewModel = hiltViewModel(),
    employeeViewModel: EmployeeViewModel = hiltViewModel()
) {
    var (title, setTitle) = remember { mutableStateOf("") }
    var (description, setDescription) = remember { mutableStateOf("") }
    var (date, setDate) = remember { mutableStateOf("") }
    val (role, setRole) = remember { mutableStateOf("") }
    val (roleId, setRoleId) = remember { mutableStateOf(0) }

    var (isTitleValid, setTitleValid) = remember { mutableStateOf(false) }
    val (isDescriptionValid, setDescriptionValid) = remember { mutableStateOf(false) }

    val user = authViewModel.userData.collectAsState()
    if (user.value?.jwt != null) {
        employeeViewModel.getJobRoles(user?.value?.jwt!!)
    }

    val jobRoles = employeeViewModel.jobRolesData.collectAsState()

    var announcementResponse = announcementViewModel.addAnnouncementState.collectAsState()

    fun addAnnouncement() {
        if (!isTitleValid || !isDescriptionValid || date.isEmpty() || role.isEmpty()) {
            Toast.makeText(navController.context, "Please fill all the fields", Toast.LENGTH_SHORT)
                .show()
        } else {
            announcementViewModel.addAnnouncement(title, description, date, roleId, null)
        }
    }

    when (announcementResponse.value) {
        is Result.Success -> {
            if (navController.currentDestination?.route != Screen.HrAnnouncementScreen.route) {
                navController.navigate(Screen.HrAnnouncementScreen.route) {
                    popUpTo(Screen.HrAnnouncementScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
        is Result.Error -> {
            val error = (announcementResponse.value as Result.Error)
            Toast.makeText(
                navController.context, error.message, Toast.LENGTH_SHORT
            ).show()
        }
        else -> {}
    }

    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        mContext, { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            if (mMonth < 9) {
                setDate("$mYear-0${mMonth + 1}-$mDayOfMonth")
            } else {
                setDate("$mYear-${mMonth + 1}-$mDayOfMonth")
            }
        }, mYear, mMonth, mDay
    )

    var jobRolesList = emptyList<itemDropdown>()

    when (jobRoles.value) {
        is Result.Success -> {
            val data = (jobRoles.value as Result.Success<JobRolesResponse>).data
            jobRolesList = data?.data?.map {
                itemDropdown(id = it?.id!!, value = it?.attributes?.name!!)
            } ?: emptyList()
        }
        else -> {}
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp)
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
                    text = "Make an Announcement",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        item {
            Column(
                modifier = Modifier.padding(top = 24.dp)
            ) {
                TextInput(onValueChange = {
                    setTitle(it)
                },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    label = "Announcement Title",
                    type = "text",
                    value = title,
                    placeholder = "Daily Meetings",
                    isValid = {
                        setTitleValid(it)
                    }
                )
                TextInput(onValueChange = {
                    setDescription(it)
                },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .height(150.dp)
                        .fillMaxWidth(),
                    label = "Announcement Description",
                    type = "text",
                    value = description,
                    placeholder = "Enter announcement details",
                    isValid = {
                        setDescriptionValid(it)
                    }
                )
                TextInput(
                    value = date,
                    onValueChange = setDate,
                    label = "Date",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            mDatePickerDialog.show()
                        },
                    enabled = false,
                    placeholder = "DD/MM/YYYY",
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Role",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(bottom = 18.dp)
                            .fillMaxWidth()
                    )
                    Dropdown(
                        value = role, onItemClick = {
                            setRole(it.value)
                            setRoleId(it.id)
                        }, dropDownItems = jobRolesList, placeholder = "Select Role"
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
                CustomButton(
                    onClick = {
                        addAnnouncement()
                    }, text = "Add Announcement",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HrAnnouncementFormScreenPreview() {
    HrAnnouncementFormScreen()
}