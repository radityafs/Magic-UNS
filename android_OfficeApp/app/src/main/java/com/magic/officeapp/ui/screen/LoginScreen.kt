package com.magic.officeapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.component.TextInput
import com.magic.officeapp.ui.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController = rememberNavController(),
) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isValidData = remember { mutableStateOf(false) }

    Scaffold(
        Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {

            Row(modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)) {
                Text(text = "Welcome Back", fontSize = 20.sp, fontWeight = FontWeight.W600, letterSpacing = 2.sp)
            }

            Row(modifier = Modifier
                .padding(top = 12.dp, bottom = 30.dp)
                .fillMaxWidth()) {
                Text(text = "Sign in to continue", fontSize = 16.sp, letterSpacing = 1.sp)
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextInput(
                    onValueChange = {},
                    label = "Email",
                    type = "text",
                    value="radityafiqa",
                    placeholder = "Enter your email",
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)){
                TextInput(
                    onValueChange = {},
                    label = "Email",
                    type = "text",
                    value="radityafiqa",
                    placeholder = "Enter your email",
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Or Sign In With", fontSize = 12.sp, fontWeight = FontWeight.ExtraLight)
            }

            Row(modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Google", fontSize = 12.sp, fontWeight = FontWeight.ExtraLight)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Facebook", fontSize = 12.sp, fontWeight = FontWeight.ExtraLight)
                }

            }

            Row(modifier = Modifier.fillMaxWidth()){
                CustomButton(
                    text = "Login",
                    onClick = {},
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                        .height(55.dp)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}