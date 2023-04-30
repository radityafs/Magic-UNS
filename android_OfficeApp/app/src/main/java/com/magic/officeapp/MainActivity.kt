package com.magic.officeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.ui.component.BottomNavigationBar
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.screen.*
import com.magic.officeapp.ui.theme.Grey700
import com.magic.officeapp.ui.theme.OfficeAppTheme
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OfficeAppTheme {
                Scaffold {

                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    val authViewModel = hiltViewModel<AuthViewModel>(this)
                    val isLogged by authViewModel.isLogged.collectAsState()

                    Scaffold(
                        bottomBar = {
                            if (currentRoute == Screen.HomeScreen.route || currentRoute == Screen.AnnouncementScreen.route) {
                                BottomNavigationBar(navController = navController)
                            }
                        },
                        floatingActionButton = {
                            if (currentRoute == Screen.HomeScreen.route ||  currentRoute == Screen.AnnouncementScreen.route) {

                                FloatingActionButton(
                                    onClick = { /*TODO*/ },
                                    backgroundColor = Grey700,
                                    modifier = Modifier.size(68.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.state_icon),
                                        contentDescription = "Check",
                                        tint = Color.White,
                                        modifier = Modifier.size(48.dp)
                                    )
                                }
                            }
                        },
                        floatingActionButtonPosition = FabPosition.Center,
                        isFloatingActionButtonDocked = true,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = if (isLogged) Screen.HomeScreen.route else Screen.SplashScreen.route
                        ) {
                            composable(Screen.SplashScreen.route) {
                                SplashScreen(navController = navController)
                            }
                            composable(Screen.LoginScreen.route) {
                                LoginScreen(navController = navController)
                            }
                            composable(Screen.HomeScreen.route) {
                                HomeScreen(navController = navController)
                            }
                            composable(Screen.AttendanceScreen.route) {
                                AttendanceScreen(navController = navController)
                            }
                            composable(Screen.RequestScreen.route) {
                                RequestScreen(navController = navController)
                            }
                            composable(Screen.RequestFormScreen.route) {
                                RequestFormScreen(navController = navController)
                            }

                            composable(Screen.PayrollScreen.route) {
                                PayrollScreen(navController = navController)
                            }

                            composable(Screen.PayrollDetailScreen.route) {
                                PayrollDetailScreen(navController = navController)
                            }

                            composable(Screen.AnnouncementScreen.route) {
                                AnnouncementScreen(navController = navController)
                            }
                            composable(Screen.ProfileScreen.route) {
                                ProfileScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}