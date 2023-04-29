package com.magic.officeapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.component.TextInput
import com.magic.officeapp.R
import com.magic.officeapp.ui.navigation.Screen
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController = rememberNavController(),
) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (isValidData, setIsValidData) = remember { mutableStateOf(false) }

    Scaffold(
        Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
            ) {
                Text(
                    text = "Hi, Welcome Back! \uD83D\uDC4B",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,

                    )
            }

            Row(
                modifier = Modifier
                    .padding(top = 14.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Happy to see you again, please login here.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp)
            ) {
                TextInput(
                    onValueChange = {
                        setEmail(it)
//                        isValidData.value = email.value.isNotEmpty()
                    },
                    label = "Email",
                    type = "email",
                    value = email,
                    placeholder = "Enter your email",
                    isValid = {
                        setIsValidData(it)
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                TextInput(
                    onValueChange = {
                        setPassword(it)
                    },
                    isValid = {
                        setIsValidData(it)
                    },
                    label = "Password",
                    type = "password",
                    value = password,
                    placeholder = "Enter your paspsword"
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Forget Password?",
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .clickable { },
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                CustomButton(
                    text = "Login",
                    onClick = {
                        navController.navigate(Screen.HomeScreen.route)
                    },
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                        .height(55.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Don\'t have an account? ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    "Register",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .clickable { }
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Or Sign In With",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                )
            }

            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.facebook
                    ),
                    contentDescription = "Login with facebook",
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable { }
                )
                Image(
                    painter = painterResource(
                        id = R.drawable.google
                    ),
                    contentDescription = "Login with facebook",
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable { }
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