package com.magic.officeapp.ui.screen.employee

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.magic.officeapp.ui.component.CardPayroll
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.ui.viewmodel.PayrollViewModel
import com.magic.officeapp.utils.constants.Result

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PayrollScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
    payrollViewModel: PayrollViewModel = hiltViewModel()
) {
    val user = authViewModel.userData.collectAsState().value
    val payrollState = payrollViewModel.allPayrollByUserState.collectAsState().value
    val payrollByUser = payrollViewModel.allPayrollByUser.collectAsState().value

    when (payrollState) {
        is Result.Empty -> {
            if (user?.id != null) {
                payrollViewModel.getAllPayrollbyUser(user.id.toString())
            }
        }
        is Result.Error -> {
            Toast.makeText(navController.context, "Error", Toast.LENGTH_SHORT).show()
        }
        is Result.Success -> {
            Log.d("TAG", "PayrollScreen: ${payrollState.data}")
        }
        else -> {}
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
                    text = "Payroll",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.payroll_animation),
                contentDescription = "Payroll Animation",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
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

        }

        payrollByUser?.forEach { payroll ->
            item {
                CardPayroll(
                    salaryGross = payroll?.attributes?.user?.data?.attributes?.salary ?: "0",
                    salaryNet = payroll?.attributes?.totalSalary ?: "0",
                    created_at = payroll?.attributes?.createdAt!!,
                    salaryDate = payroll?.attributes?.month!!,
                    onClick = {
                        navController.navigate(Screen.PayrollDetailScreen.route + "/${payroll.id}")
                    }
                )
            }
        }

    }
}

