package com.magic.officeapp.ui.screen.hr

import Dropdown
import android.os.Build
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
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.component.TextInput
import com.magic.officeapp.ui.theme.Grey400
import com.magic.officeapp.ui.viewmodel.EmployeeViewModel
import com.magic.officeapp.utils.constants.Result
import com.magic.officeapp.utils.salaryMonth
import itemDropdown

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HrPayrollFormScreen(
    navController: NavController = rememberNavController(),
    employeeViewModel: EmployeeViewModel = hiltViewModel()
) {

    val (employeSelected, setEmployeSelected) = remember {
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

    employeeViewModel.getEmployeeList("")
    val employees = employeeViewModel.employeeData.collectAsState().value
    val employeeResponse = employeeViewModel.getEmployeeListResponse.collectAsState().value

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
                value = employeSelected.value,
                dropDownItems = employeeDropdown.value,
                onItemClick = {
                    setEmployeSelected(it)
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
                onClick = { /*TODO*/ }, text = "Add Payroll"
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