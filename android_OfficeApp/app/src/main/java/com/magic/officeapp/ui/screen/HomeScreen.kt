package com.magic.officeapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.CustomCard
import com.magic.officeapp.ui.component.Menu
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.theme.Grey800


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
) {

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
                            text = "Morning, Raditya \uD83E\uDD1F\uD83C\uDFFB",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                        Text(text = "18 April 2023", modifier = Modifier.padding(top = 10.dp))
                    }

                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .clickable {
                                navController.navigate(Screen.ProfileScreen.route)
                            }
                    )
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
                Menu()
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Recent Activity", fontWeight = FontWeight.Bold, fontSize = 18.sp)
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
                CustomCard(
                    title = "Request",
                    created_at = "2021-09-09 12:00:00",
                    icon = 0,
                    onClick = {})

                Divider(
                    modifier = Modifier.fillMaxWidth().height(1.dp),
                    color = Color("#F0F1F3".toColorInt())
                )
            }
        }

        item{
            Spacer(modifier = Modifier.padding(bottom = 100.dp))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}