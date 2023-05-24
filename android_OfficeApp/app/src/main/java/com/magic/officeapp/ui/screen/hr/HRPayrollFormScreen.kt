package com.magic.officeapp.ui.screen.hr

import Dropdown
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.data.model.request.PayrollRequest
import com.magic.officeapp.data.model.request.PayrollRequestData
import com.magic.officeapp.data.model.response.AttendanceResponse
import com.magic.officeapp.data.model.response.AttendanceResponseDataItem
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.component.TextInput
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.theme.Grey400
import com.magic.officeapp.ui.viewmodel.AttendanceViewModel
import com.magic.officeapp.ui.viewmodel.EmployeeViewModel
import com.magic.officeapp.ui.viewmodel.PayrollViewModel
import com.magic.officeapp.utils.*
import com.magic.officeapp.utils.constants.Result
import com.magic.officeapp.utils.constants.utcToFormat
import itemDropdown

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HrPayrollFormScreen(
    navController: NavController = rememberNavController(),
    employeeViewModel: EmployeeViewModel = hiltViewModel(),
    attendanceViewModel: AttendanceViewModel = hiltViewModel(),
    payrollViewModel: PayrollViewModel = hiltViewModel()
) {

    val loading = remember {
        mutableStateOf(false)
    }

    val attendanceUserByDate = attendanceViewModel.attendanceUserByDate.collectAsState().value
    val insertPayrollResponse = payrollViewModel.insertPayrollDataResponse.collectAsState().value
    val employees = employeeViewModel.employeeData.collectAsState().value
    val employeeResponse = employeeViewModel.getEmployeeListResponse.collectAsState().value

    val (employeeSelected, setEmployeeSelected) = remember {
        mutableStateOf(itemDropdown(0, ""))
    }

    val (monthSelected, setMonthSelected) = remember {
        mutableStateOf(itemDropdown(0, ""))
    }
    val (bonus, setBonus) = remember {
        mutableStateOf("")
    }

    val (deduction, setDeduction) = remember {
        mutableStateOf("")
    }

    val (tax, setTax) = remember {
        mutableStateOf("")
    }

    val employeeDropdown = remember {
        mutableStateOf(emptyList<itemDropdown>())
    }

    when (insertPayrollResponse) {
        is Result.Success -> {
            Toast.makeText(
                navController.context,
                "Berhasil menambahkan payroll",
                Toast.LENGTH_SHORT
            )
                .show()

            if (navController.currentDestination?.route == Screen.HrPayrollFormScreen.route) {
                navController.popBackStack()
            }
        }
        is Result.Error -> {
            Toast.makeText(
                navController.context,
                "Gagal menambahkan payroll",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        else -> {
        }
    }


    when (attendanceUserByDate) {
        is Result.Success -> {
            val data = attendanceUserByDate.data.data as List<AttendanceResponseDataItem>
            val monthSummary = monthStartEnd(monthSelected.value)
            val attendanceEmployeeSummary =
                attendanceSummarybyMonth(
                    dateToUTC(monthSummary.start), dateToUTC(monthSummary.end),
                    data
                )

            val employeeSalary = employees.find { it.id == employeeSelected.id }?.salary

            val salaryMonth = countSalary(employeeSalary?.toLong()!!, attendanceEmployeeSummary)
            val totalTax = countTax((salaryMonth + bonus.toInt()), tax)

            if (insertPayrollResponse is Result.Empty) {
                payrollViewModel.insertPayroll(
                    PayrollRequest(
                        PayrollRequestData(
                            user = employeeSelected.id.toString(),
                            month = dateToUTC(monthSummary.start),
                            bonus = bonus,
                            workDays = attendanceEmployeeSummary.present.toString(),
                            workSalary = salaryMonth.toString(),
                            deduction = deduction,
                            tax = totalTax.toString(),
                            totalSalary = (salaryMonth + bonus.toInt() - deduction.toInt() - totalTax).toString()
                        )
                    )
                )
            }
        }
        is Result.Error -> {
            Toast.makeText(
                navController.context,
                "Gagal mengambil data absensi",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        else -> {

        }
    }

    fun addPayroll() {
        val monthSummary = monthStartEnd(monthSelected.value)

        attendanceViewModel.getAttendanceUserByDate(
            employeeSelected.id.toString(),
            monthSummary.start,
            monthSummary.end
        )

    }

    employeeViewModel.getEmployeeList("")


    when (employeeResponse) {
        is Result.Success -> {
            employeeDropdown.value = employees.map {
                itemDropdown(
                    it.id!!, "${it.username} - ${
                        it.jobRole?.name
                    }"
                )
            }
        }
        else -> {}
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
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
                    text = "Add Payroll",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }

        item {

            Text(text = "Employee Name", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))
            Dropdown(
                value = employeeSelected.value,
                dropDownItems = employeeDropdown.value,
                onItemClick = {
                    setEmployeeSelected(it)
                },
                placeholder = "Select Employee"
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            Text(text = "Month", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))
            Dropdown(
                value = monthSelected.value,
                dropDownItems = salaryMonth(),
                onItemClick = {
                    setMonthSelected(it)
                },
                placeholder = "Select Month"
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Earnings",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Grey400
            )
            Spacer(modifier = Modifier.height(24.dp))

            TextInput(value = bonus, onValueChange = {
                setBonus(it)
            }, label = "Bonus", placeholder = "Bonus", type = "number")
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Deductions",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Grey400
            )
            Spacer(modifier = Modifier.height(24.dp))

            TextInput(value = deduction, onValueChange = {
                setDeduction(it)
            }, label = "Deductions", placeholder = "Deductions", type = "number")

            Spacer(modifier = Modifier.height(24.dp))

            TextInput(value = tax, onValueChange = {
                setTax(it)
            }, label = "Tax", placeholder = "Tax (%)", type = "number")
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                onClick = {
                    addPayroll()
                }, text = "Add Payroll"
            )

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HrPayrollFormScreenPreview() {
    HrPayrollFormScreen()
}