package com.magic.officeapp.ui.screen.hr

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.theme.Grey100
import com.magic.officeapp.ui.theme.Grey700
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
//    viewModel: AuthViewModel = hiltViewModel()
) {

//    val user = viewModel.userData.collectAsState()
//    val currentHour = Time(System.currentTimeMillis()).hours
//    fun greeting(
//        username: String = "Employee",
//    ): String {
//        val userName = username.split(" ")[0]
//        when (currentHour) {
//            in 5..11 -> {
//                return "Morning, $userName \uD83E\uDD1F\uD83C\uDFFB"
//            }
//            in 12..15 -> {
//                return "Afternoon, $userName \uD83C\uDF1E"
//            }
//            in 16..20 -> {
//                return "Evening, $userName \uD83C\uDF1A"
//            }
//        }
//        return "Night, $userName \uD83C\uDF19"
//    }
//
//    fun currentDate() = SimpleDateFormat("dd MMMM yyyy").format(Date())

    LazyColumn(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxSize()
    ) {
        item {
            Column() {
                Row(
                    modifier = Modifier
                        .padding(top = 52.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            text = "Hello, Employee", fontWeight = FontWeight.Bold, fontSize = 24.sp
                        )
                        Text(text = "11 April 2023", modifier = Modifier.padding(top = 10.dp))
                    }

                    Image(painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .clickable {
                                navController.navigate(Screen.ProfileScreen.route)
                            })
                }

            }
        }

        item {
            Spacer(modifier = Modifier.height(33.dp))
        }

        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Card(
                    modifier = Modifier
                        .height(108.dp)
                        .weight(1f)
                        .padding(end = 6.dp),
                    backgroundColor = Color("#292D35".toColorInt()),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Request",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 10.sp,
                                color = Color.White
                            )

                            Image(
                                painter = painterResource(id = R.drawable.back_icon_white),
                                contentDescription = "Back Icon",
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                "43",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text("Request Waiting", fontSize = 10.sp, color = Grey100)
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .height(108.dp)
                        .weight(1f)
                        .padding(start = 6.dp),
                    backgroundColor = Color("#525A6A".toColorInt()),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Employee",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 10.sp,
                                color = Color.White
                            )

                            Image(
                                painter = painterResource(id = R.drawable.back_icon_white),
                                contentDescription = "Back Icon",
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                "43",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text("Active Employee", fontSize = 10.sp, color = Grey100)
                        }

                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(24.dp, 16.dp)
            ) {

            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}