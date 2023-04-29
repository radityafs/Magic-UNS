package com.magic.officeapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.CustomIcon

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavController = rememberNavController(),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 30.dp)
    ) {
        Box(modifier = Modifier
            .clickable {
                navController.popBackStack()
            }
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Back Icon",
                    modifier = Modifier
                        .height(20.dp)
                        .offset(x = 0.dp, y = 4.dp)
                )

                Text(
                    text = "Profile",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .offset(x = 0.dp, y = 30.dp)
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clickable {}

                )

                Text(
                    "Raditya Firman Syaputra",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 27.dp)
                        .fillMaxWidth()
                )

                Text(
                    "Developer",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                )

                Text(
                    "Advanced Settings",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 20.dp)
                )

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(bottom = 10.dp)
                    .border(
                        1.dp,
                        color = Color("#F0F1F3".toColorInt()),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable {}
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.lock_icon),
                            contentDescription = "Lock Icon",
                            modifier = Modifier
                                .height(48.dp)
                                .offset(x = 4.dp, y = 3.dp)
                        )

                        Text(
                            "Chnage passowrd",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(start = 8.dp, top = 15.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        Image(
                            painter = painterResource(id = R.drawable.right_icon),
                            contentDescription = "Lock Icon",
                            modifier = Modifier
                                .height(48.dp)
                                .padding(end = 4.dp, top = 3.dp)
                        )
                    }
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(bottom = 10.dp)
                    .border(
                        1.dp,
                        color = Color("#F0F1F3".toColorInt()),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable {}
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.logout_icon),
                            contentDescription = "Logout Icon",
                            modifier = Modifier
                                .height(48.dp)
                                .offset(x = 4.dp, y = 3.dp)
                        )

                        Text(
                            "Logout",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(start = 8.dp, top = 15.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        Image(
                            painter = painterResource(id = R.drawable.right_icon),
                            contentDescription = "Lock Icon",
                            modifier = Modifier
                                .height(48.dp)
                                .padding(end = 4.dp, top = 3.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}