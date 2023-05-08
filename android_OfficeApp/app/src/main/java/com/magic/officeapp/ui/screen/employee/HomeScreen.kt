package com.magic.officeapp.ui.screen.employee

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.magic.officeapp.ui.component.CustomCard
import com.magic.officeapp.ui.component.Menu
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.theme.Grey700
import com.magic.officeapp.ui.theme.Grey800
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Date


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    viewModel: AuthViewModel = hiltViewModel()
) {
    val user = viewModel.userData.collectAsState()
    val currentHour = Time(System.currentTimeMillis()).hours
    fun greeting(
        username: String = "Employee",
    ): String {
        val userName = username.split(" ")[0]
        when (currentHour) {
            in 5..11 -> {
                return "Morning, $userName \uD83E\uDD1F\uD83C\uDFFB"
            }
            in 12..15 -> {
                return "Afternoon, $userName \uD83C\uDF1E"
            }
            in 16..20 -> {
                return "Evening, $userName \uD83C\uDF1A"
            }
        }
        return "Night, $userName \uD83C\uDF19"
    }
    fun currentDate() = SimpleDateFormat("dd MMMM yyyy").format(Date())

    LazyColumn(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxWidth()
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
                            text = greeting(
                                username = user.value?.username ?: "Employee"
                            ), fontWeight = FontWeight.Bold, fontSize = 24.sp
                        )
                        Text(text = currentDate(), modifier = Modifier.padding(top = 10.dp))
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
            Column(modifier = Modifier.padding(top = 20.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Grey800)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(verticalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "My Pay Slip", color = Color.White)
                            Text(
                                text = "Rp 6.750.000",
                                modifier = Modifier.padding(top = 10.dp),
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        }

                        Image(
                            painter = painterResource(id = R.drawable.fluent_money_filled),
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                    }
                }
            }
        }
        item {

            Column(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 40.dp)
                    .border(1.dp, Color("#F0F1F3".toColorInt()), RoundedCornerShape(8.dp))
                    .fillMaxWidth()
            ) {
                Menu(
                    navController = navController,
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Recent Activity", fontWeight = FontWeight.Bold, fontSize = 18.sp
                    )
                    Text(text = "See more", fontWeight = FontWeight.Normal, fontSize = 14.sp)
                }
            }

        }
        items(4, key = { index -> index }) { index ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                CustomCard(title = "Request",
                    created_at = "2021-09-09 12:00:00",
                    icon = 0,
                    onClick = {})

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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}