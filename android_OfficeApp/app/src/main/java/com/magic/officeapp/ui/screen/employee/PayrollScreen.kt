package com.magic.officeapp.ui.screen.employee

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.CardPayroll
import com.magic.officeapp.ui.component.CustomButton

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PayrollScreen(
    navController: NavController = rememberNavController(),
) {

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

        items(4, key = { index -> index }) { index ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                CardPayroll(
                    salaryGross = "5500000",
                    salaryNet = "5750000",
                    created_at = "2021-08-01T00:00:00.000Z",
                    salaryDate = "2021-08-01T00:00:00.000Z",
                    onClick = {}
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color = Color("#F0F1F3".toColorInt())
                )
            }
        }

        item {
            Spacer(modifier = Modifier.padding(bottom = 100.dp))
        }

    }
}

