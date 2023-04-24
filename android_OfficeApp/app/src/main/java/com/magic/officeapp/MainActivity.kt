package com.magic.officeapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.ui.component.BottomNavigationBar
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.screen.SplashScreen
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
                            if (currentRoute != Screen.SplashScreen.route) {
                                BottomNavigationBar(navController = navController)
                            }
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = if (isLogged) Screen.HomeScreen.route else Screen.SplashScreen.route
                        ) {

                            composable(Screen.SplashScreen.route) {
                                SplashScreen(navController = navController)
                            }

                            composable(Screen.HomeScreen.route) {
                                Text(text = "Home")
                            }
                            composable(Screen.AttendanceScreen.route) {
                                Text(text = "Attendance")
                            }

                            composable(Screen.AnnouncementScreen.route) {
                                Text(text = "Announcement")
                            }

                            composable(Screen.ProfileScreen.route) {
                                Text(text = "Profile")
                            }
                        }
                    }
                }


            }
        }
    }
}