package com.magic.officeapp.ui.screen.hr

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.*
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.viewmodel.AttendanceViewModel
import com.magic.officeapp.ui.viewmodel.PayrollViewModel
import com.magic.officeapp.utils.constants.Result

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HrPayrollScreen(
    navController: NavController = rememberNavController(),
    payrollViewModel: PayrollViewModel = hiltViewModel()
) {
    var (search, setSearch) = remember {
        mutableStateOf("")
    }

    val allPayrollData = payrollViewModel.allPayrollData.collectAsState().value
    val allPayrollState = payrollViewModel.allPayrollDataState.collectAsState().value

    when (allPayrollState) {
        is Result.Empty -> {
            payrollViewModel.getAllPayrollData()
        }
        is Result.Error -> {
            Toast.makeText(
                navController.context,
                allPayrollState.message,
                Toast.LENGTH_SHORT
            ).show()
        }
        is Result.Success -> {
            Log.d("TAG", "HrPayrollScreen: ${allPayrollState.data}")
        }
    }



    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            CustomButton(
                onClick = {
                    navController.navigate(Screen.HrPayrollFormScreen.route)
                },
                text = "Add Payroll",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(start = 24.dp, end = 24.dp)
            )
        },

        ) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .padding(top = 52.dp)
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
                        text = "Payroll Activity",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Recent Payroll",
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

                Spacer(modifier = Modifier.height(16.dp))
                TextInput(
                    value = search,
                    onValueChange = {
                        setSearch(it)
                    },
                    placeholder = "Search Item",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    }
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )

            }

            allPayrollData?.forEach {
                item {
                    CardPayrollHR(
                        userName = it?.attributes?.user?.data?.attributes?.username.toString(),
                        salaryDate = it?.attributes?.month.toString(),
                        salaryNet = it?.attributes?.totalSalary.toString(),
                        salaryGross = it?.attributes?.user?.data?.attributes?.salary.toString(),
                        onClick = {
                            navController.navigate(Screen.PayrollDetailScreen.route + "/${it?.id}")
                        }
                    )
                }
            }
        }
    }
}

