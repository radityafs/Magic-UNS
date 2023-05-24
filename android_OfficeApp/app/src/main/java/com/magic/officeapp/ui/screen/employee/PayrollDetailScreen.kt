package com.magic.officeapp.ui.screen.employee

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.numberToCurrency
import com.magic.officeapp.ui.theme.Grey400
import com.magic.officeapp.ui.theme.Grey800
import com.magic.officeapp.ui.viewmodel.PayrollViewModel
import com.magic.officeapp.utils.constants.Result
import com.magic.officeapp.utils.constants.utcToFormat

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PayrollDetailScreen(
    navController: NavController = rememberNavController(),
    payrollViewModel: PayrollViewModel = hiltViewModel(),
    id: Int? = 0
) {

    val detailPayrollState = payrollViewModel.payrollDetailState.collectAsState().value
    val detailPayrollData = payrollViewModel.payrollDetail.collectAsState().value
    var (grossSalary, setGrossSalary) = remember {
        mutableStateOf(0)
    }
    var (absentSalary, setAbsentSalary) = remember {
        mutableStateOf(0)
    }
    var (totalDeduction, setTotalDeduction) = remember {
        mutableStateOf(0)
    }

    when (detailPayrollState) {
        is Result.Empty -> {
            payrollViewModel.getPayrollById(id.toString())
        }
        is Result.Error -> {
            Toast.makeText(
                navController.context,
                "Terjadi kesalahan",
                Toast.LENGTH_SHORT
            ).show()
        }
        is Result.Success -> {
            var bonus = detailPayrollData?.data?.attributes?.bonus?.toInt()
            var salary =
                detailPayrollData?.data?.attributes?.user?.data?.attributes?.salary?.toInt()
            var workSalary = detailPayrollData?.data?.attributes?.workSalary?.toInt()
            var totalSalary = detailPayrollData?.data?.attributes?.totalSalary?.toInt()
            if (bonus != null && salary != null) {
                setGrossSalary(bonus + salary)
            }

            if (workSalary != null && salary != null) {
                setAbsentSalary(salary - workSalary)
            }

            if (totalSalary != null && grossSalary != null) {
                setTotalDeduction(totalSalary - grossSalary)
            }

        }
        else -> {

        }

    }


    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxSize()
    ) {
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
                text = "Payslip for ${utcToFormat(detailPayrollData?.data?.attributes?.month, "MMMM yyyy")}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Payslip Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp, Grey400, RoundedCornerShape(16.dp)
                )
                .padding(vertical = 24.dp, horizontal = 16.dp),
            elevation = 0.dp
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "My Payslip", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

                Spacer(modifier = Modifier.height(28.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Name", fontSize = 14.sp, color = Grey400)
                    Text(
                        text = detailPayrollData?.data?.attributes?.user?.data?.attributes?.username.toString(),
                        fontSize = 14.sp,
                        color = Grey800
                    )
                }

                //employee id
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Employee ID", fontSize = 14.sp, color = Grey400)
                    Text(
                        text = detailPayrollData?.data?.attributes?.user?.data?.id.toString()
                            ?: "0",
                        fontSize = 14.sp,
                        color = Grey800
                    )
                }

                //Posititon
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Position", fontSize = 14.sp, color = Grey400)
                    Text(
                        text = detailPayrollData?.data?.attributes?.user?.data?.attributes?.jobRole?.data?.attributes?.name
                            ?: "Employee",
                        fontSize = 14.sp,
                        color = Grey800
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Earnings", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }

                Spacer(modifier = Modifier.height(16.dp))

                //Basic Salary
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Basic Salary", fontSize = 14.sp, color = Grey400)
                    Text(
                        text = numberToCurrency(
                            detailPayrollData?.data?.attributes?.user?.data?.attributes?.salary
                                ?: "0"
                        ), fontSize = 14.sp, color = Grey800
                    )
                }

                //Bonus
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Bonus", fontSize = 14.sp, color = Grey400)
                    Text(
                        text = numberToCurrency(
                            detailPayrollData?.data?.attributes?.bonus ?: "0"
                        ), fontSize = 14.sp, color = Grey800
                    )
                }

                // Gross Salary
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Gross Salary",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = numberToCurrency(grossSalary.toString()),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }


                // Deductions
                Spacer(modifier = Modifier.height(28.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "Deductions",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Late or Absent
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Absent/Late", fontSize = 14.sp, color = Grey400)
                    Text(
                        text = numberToCurrency(absentSalary.toString()),
                        fontSize = 14.sp,
                        color = Grey800
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Late or Absent
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Deductions", fontSize = 14.sp, color = Grey400)
                    Text(
                        text = numberToCurrency(
                            detailPayrollData?.data?.attributes?.deduction ?: "0"
                        ),
                        fontSize = 14.sp,
                        color = Grey800
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                //Tax
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Tax", fontSize = 14.sp, color = Grey400)
                    Text(
                        text = numberToCurrency(
                            detailPayrollData?.data?.attributes?.tax ?: "0"
                        ),
                        fontSize = 14.sp,
                        color = Grey800
                    )
                }

                // total deductions
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total Deductions",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text =
                        numberToCurrency(
                            totalDeduction.toString()
                        ),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Net Salary
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Net Salary",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = numberToCurrency(
                            detailPayrollData?.data?.attributes?.totalSalary ?: "0"
                        ),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color("#37A345".toColorInt())
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

    }
}
