package com.magic.officeapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SplashScreen(
    navController: NavController = rememberNavController(),
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(1f),
                ) {
                    Column(
                        Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth()
                            .weight(1.5f)
                            .background(Color("#FEF2E8".toColorInt()))
                    ) {
                        Text(text = "Hello World")
                    }
                    Column(
                        Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                            .height(150.dp)
                            .weight(1f)
                            .background(Color("#F1F8EC".toColorInt()))
                    ) {
                        Text(text = "Hello World")
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(1f)
                ) {

                    Column(
                        Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth()
                            .height(150.dp)
                            .weight(1f)
                            .background(Color("#F1F8EC".toColorInt()))
                    ) {
                        Text(text = "Hello World")
                    }

                    Column(
                        Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                            .weight(1.5f)
                            .background(Color("#FEF2E8".toColorInt()))
                    ) {
                        Text(text = "Hello World")
                    }

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 28.sp,
                    color = Color("#232832".toColorInt()),
                    text = "Reduce the workloads\n" + "of Office Employee."
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp, bottom = 0.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp,
                    color = Color("#232832".toColorInt()),
                    text = "Help you to improve efficiency, accuracy\n" + "engagement, and cost savings for employers"
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color("#333D55".toColorInt()), contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(20)
                ) {
                    Text(text = "I'm a manager.")
                }
            }


            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(
                            2.dp, Color("#21242380".toColorInt()), shape = RoundedCornerShape(20)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White, contentColor = Color("#333D55".toColorInt())
                    ),
                    shape = RoundedCornerShape(20)
                ) {
                    Text(text = "I'm a employee.")
                }
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}