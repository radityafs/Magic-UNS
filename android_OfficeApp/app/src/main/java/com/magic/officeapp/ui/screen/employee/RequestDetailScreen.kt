package com.magic.officeapp.ui.screen.employee

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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

@Composable
fun RequestDetailScreen(
    navController: NavController = rememberNavController(),
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
                text = "Payroll",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.state_success),
                contentDescription = "Success",
                modifier = Modifier.size(72.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Request Approved",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(modifier = Modifier.height(40.dp))
        }

        Column {
            Text(
                text = "Request Type",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt())
            )
            Text(text = "Permit", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(24.dp))
        }

        Column {
            Text(
                text = "Request date",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt()),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = "1 Mei 2023", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(24.dp))
        }

        Column {
            Text(
                text = "Request time",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt()),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = "09:15 am", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(24.dp))
        }

        Column {
            Text(
                text = "Description",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt()),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "My doctor has recommended that I take a leave of absence for medical reasons.",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        Column {
            Text(
                text = "Permit Date",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt()),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "3 Mei 2023",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(32.dp))
        }


        Column(modifier = Modifier.fillMaxWidth()) {
            CustomButton(
                onClick = {
                    navController.popBackStack()
                },
                text = "Return to Request List",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RequestDetailScreenPreview() {
    RequestDetailScreen()
}