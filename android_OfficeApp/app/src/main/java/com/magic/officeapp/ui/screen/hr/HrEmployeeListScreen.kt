package com.magic.officeapp.ui.screen.hr

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
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
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.component.TextInput
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.ui.viewmodel.EmployeeViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HrEmployeeListScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
    employeeViewModel: EmployeeViewModel = hiltViewModel()
) {
    val (search, setSearch) = remember {
        mutableStateOf("")
    }

    val user = authViewModel.userData.collectAsState()

    employeeViewModel.getEmployeeList(user.value?.jwt ?: "")

    val employees = employeeViewModel.employeeData.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp),
        floatingActionButton = {
            CustomButton(
                onClick = {
                    navController.navigate(Screen.HRAddEmployeeScreen.route)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), text = "Add Employee"
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        LazyColumn {
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
                        text = "Employee List",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
            }

            item {
                TextInput(
                    value = search,
                    onValueChange = setSearch,
                    placeholder = "Search Employee",
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            employees.value.forEach { employee ->
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(74.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(bottom = 4.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = employee.username ?: "Username",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    color = Color("#292D35".toColorInt())
                                )
                                Text(
                                    text = employee?.jobRole?.name ?: "Job Role",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    color = Color("#292D35".toColorInt())
                                )
                            }

                            Text(
                                text = employee?.email ?: "Email",
                                fontSize = 14.sp,
                                color = Color("#858D9D".toColorInt())
                            )
                        }
                    }

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp), color = Color.LightGray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHrEmployeeListScreen() {
    HrEmployeeListScreen()
}