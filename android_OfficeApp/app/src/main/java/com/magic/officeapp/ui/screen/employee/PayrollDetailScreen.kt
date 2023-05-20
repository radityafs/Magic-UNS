package com.magic.officeapp.ui.screen.employee

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.theme.Grey400
import com.magic.officeapp.ui.theme.Grey800

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PayrollDetailScreen(
    navController: NavController = rememberNavController(),
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            CustomButton(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                text = "Back to Payroll",
                onClick = {
                    navController.popBackStack()
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) {
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
                    text = "Payslip for May 2023",
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
                    .padding(vertical = 24.dp, horizontal = 16.dp)
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
                        Text(text = "John Doe", fontSize = 14.sp, color = Grey800)
                    }

                    //employee id
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Employee ID", fontSize = 14.sp, color = Grey400)
                        Text(text = "123456", fontSize = 14.sp, color = Grey800)
                    }

                    //Posititon
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Position", fontSize = 14.sp, color = Grey400)
                        Text(text = "Software Engineer", fontSize = 14.sp, color = Grey800)
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
                        Text(text = "Rs. 50,000", fontSize = 14.sp, color = Grey800)
                    }

                    //Allowance
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Overtime", fontSize = 14.sp, color = Grey400)
                        Text(text = "Rs. 10,000", fontSize = 14.sp, color = Grey800)
                    }

                    //Bonus
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Bonus", fontSize = 14.sp, color = Grey400)
                        Text(text = "Rs. 5,000", fontSize = 14.sp, color = Grey800)
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
                            text = "Rs. 65,000",
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
                        Text(text = "Late and Absent", fontSize = 14.sp, color = Grey400)
                        Text(text = "Rs. 2,000", fontSize = 14.sp, color = Grey800)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    //Tax
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Tax", fontSize = 14.sp, color = Grey400)
                        Text(text = "Rs. 5,000", fontSize = 14.sp, color = Grey800)
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
                        Text(text = "Rs. 7,000", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
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
                            text = "Rs. 58,000",
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
}

@Preview(showBackground = true)
@Composable
fun PayrollDetailScreenPreview() {
    PayrollDetailScreen()
}