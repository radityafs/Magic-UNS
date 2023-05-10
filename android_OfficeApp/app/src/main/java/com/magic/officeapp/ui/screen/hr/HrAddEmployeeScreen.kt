package com.magic.officeapp.ui.screen.employee

import Dropdown
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.TextInput

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.magic.officeapp.data.model.request.RegisterEmployeeRequest
import com.magic.officeapp.data.model.response.JobRolesResponse
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.screen.LoadingScreen
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.ui.viewmodel.EmployeeViewModel
import com.magic.officeapp.utils.constants.Result
import itemDropdown


@Composable
fun HrAddEmployeeScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
    employeeViewModel: EmployeeViewModel = hiltViewModel()
) {
    val (name, setName) = remember { mutableStateOf("") }
    val (email, setEmail) = remember { mutableStateOf("") }
    val (phone, setPhone) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (salary, setSalary) = remember { mutableStateOf("") }
    val (role, setRole) = remember { mutableStateOf("") }
    val (roleId, setRoleId) = remember { mutableStateOf(0) }

    val (isValidName, setIsValidName) = remember { mutableStateOf(false) }
    val (isValidEmail, setIsValidEmail) = remember { mutableStateOf(false) }
    val (isValidPhone, setIsValidPhone) = remember { mutableStateOf(false) }
    val (isValidPassword, setIsValidPassword) = remember { mutableStateOf(false) }
    val (isValidSalary, setIsValidSalary) = remember { mutableStateOf(false) }

    val user = authViewModel.userData.collectAsState()
    val jobRoles = employeeViewModel.jobRolesData.collectAsState()
    val loading = employeeViewModel.loading.collectAsState()
    val addEmployeeResponse = employeeViewModel.insertEmployeeDataResponse.collectAsState()
    var jobRolesList = emptyList<itemDropdown>()


    if (user.value?.jwt != null) {
        employeeViewModel.getJobRoles(user.value?.jwt ?: "")
    }

    fun addEmployee() {
        if (isValidName && isValidEmail && isValidPhone && isValidPassword && isValidSalary && roleId != 0) {
            employeeViewModel.insertJobRoles(
                RegisterEmployeeRequest(
                    username = name,
                    email = email,
                    phone = phone,
                    password = password,
                    salary = salary,
                    jobRole = roleId
                )
            )

            return
        }
    }


    when (jobRoles.value) {
        is Result.Success -> {
            val data = (jobRoles.value as Result.Success<JobRolesResponse>).data
            jobRolesList = data?.data?.map {
                itemDropdown(id = it?.id!!, value = it?.attributes?.name!!)
            } ?: emptyList()
        }
        else -> {}
    }

    when (addEmployeeResponse.value) {
        is Result.Success -> {
            if(navController.currentDestination?.route == Screen.HRAddEmployeeScreen.route){
                navController.popBackStack()
            }
        }
        else -> {}
    }

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 24.dp)
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
                    text = "Add Employee",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            TextInput(value = name,
                onValueChange = setName,
                label = "Name",
                placeholder = "John Doe",
                isValid = {
                    setIsValidName(it)
                })
            Spacer(modifier = Modifier.height(24.dp))

            TextInput(value = email,
                onValueChange = setEmail,
                label = "Email",
                placeholder = "example@gmail.com",
                type = "email",
                isValid = {
                    setIsValidEmail(it)
                })

            Spacer(modifier = Modifier.height(24.dp))

            TextInput(value = phone,
                onValueChange = setPhone,
                label = "Phone",
                placeholder = "628123456789",
                type = "number",
                isValid = {
                    setIsValidPhone(it)
                })
            Spacer(modifier = Modifier.height(24.dp))

            TextInput(value = password,
                onValueChange = setPassword,
                label = "Password",
                placeholder = "********",
                type = "password",
                isValid = {
                    setIsValidPassword(it)
                })
            Spacer(modifier = Modifier.height(24.dp))

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

            Spacer(modifier = Modifier.height(24.dp))

            TextInput(value = salary,
                onValueChange = setSalary,
                label = "Salary (Rp) / Month",
                placeholder = "1000000",
                type = "number",
                isValid = {
                    setIsValidSalary(it)
                })

            Spacer(modifier = Modifier.height(48.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                CustomButton(
                    onClick = {
                        if (!loading.value) {
                            addEmployee()
                        }
                    }, text = "Add Employee", modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(80.dp))
        }


    }

}


