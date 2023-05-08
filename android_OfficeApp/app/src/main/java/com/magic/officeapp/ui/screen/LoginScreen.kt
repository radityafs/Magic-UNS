package com.magic.officeapp.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.component.TextInput
import com.magic.officeapp.R
import com.magic.officeapp.data.model.LoginResponse
import com.magic.officeapp.data.model.User
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.utils.constants.Result

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController = rememberNavController(),
    viewModel: AuthViewModel = hiltViewModel()
) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (isValidEmail, setIsValidEmail) = remember { mutableStateOf(false) }
    val (isValidPassword, setIsValidPassword) = remember { mutableStateOf(false) }

    val loading = viewModel.loading.collectAsState()
    val loginResponse = viewModel.loginState.collectAsState()

    fun login() {
        if (!isValidEmail || !isValidPassword || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(navController.context, "Please fill all the fields", Toast.LENGTH_SHORT)
                .show()
        } else {
            viewModel.login(email, password)
        }
    }

    when (loginResponse.value) {
        is Result.Success -> {
            viewModel.saveUserData((loginResponse.value as Result.Success<LoginResponse>).data.user)


            if(navController.currentDestination?.route != Screen.HomeScreen.route) {
                navController.navigate(Screen.HomeScreen.route){
                    popUpTo(Screen.HomeScreen.route){
                        inclusive = true
                    }
                }
            }
        }
        is Result.Error -> {
            Toast.makeText(
                navController.context, "Password or Email is incorrect", Toast.LENGTH_SHORT
            ).show()
        }
        else -> {}
    }

    Scaffold(
        Modifier.fillMaxSize()
    ) {

        if (loading.value) {
            LoadingScreen()
        } else {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
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
                    TextInput(onValueChange = {
                        setEmail(it)
                    },
                        label = "Email",
                        type = "email",
                        value = email,
                        placeholder = "Enter your email",
                        isValid = {
                            setIsValidEmail(it)
                        })
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
                            setIsValidPassword(it)
                        },
                        label = "Password",
                        type = "password",
                        value = password,
                        placeholder = "Enter your password"
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()
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
                        text = "Login", onClick = {
                            login()
                        }, modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                            .height(55.dp)
                    )
                }

//                Row(
//                    modifier = Modifier
//                        .padding(top = 30.dp)
//                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        "Don\'t have an account? ",
//                        fontSize = 12.sp,
//                        fontWeight = FontWeight.Medium,
//                    )
//                    Text("Register",
//                        fontSize = 12.sp,
//                        fontWeight = FontWeight.Bold,
//                        modifier = Modifier
//                            .padding(bottom = 10.dp)
//                            .clickable { })
//                }

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
                    Image(painter = painterResource(
                        id = R.drawable.facebook
                    ),
                        contentDescription = "Login with facebook",
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .clickable { })
                    Image(painter = painterResource(
                        id = R.drawable.google
                    ),
                        contentDescription = "Login with facebook",
                        modifier = Modifier.clickable { })
                }

            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}