package com.magic.officeapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SplashScreen(
    navController: NavController = rememberNavController(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Image(
                painter = painterResource(id = R.drawable.splash),
                contentDescription = "Aasd",
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

            Column(
                modifier = Modifier.padding(20.dp)
            ) {
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
                        text = "Simplify Your HR Tasks and Boost Productivity"
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
                        text = "HR Management app streamlines tasks, improves productivity and engagement, and ensures compliance."
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            navController.navigate(Screen.LoginScreen.route)
                        },
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color("#333D55".toColorInt()),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(20)
                    ) {
                        Text(text = "I'm a manager.")
                    }
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            navController.navigate(Screen.LoginScreen.route) {
                                popUpTo(Screen.SplashScreen.route) {
                                    inclusive = true
                                }
                            }
                        },
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .fillMaxWidth()
                            .height(50.dp)
                            .border(
                                2.dp,
                                Color("#21242380".toColorInt()),
                                shape = RoundedCornerShape(20)
                            ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Color("#333D55".toColorInt())
                        ),
                        shape = RoundedCornerShape(20)
                    ) {
                        Text(text = "I'm a employee.")
                    }
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