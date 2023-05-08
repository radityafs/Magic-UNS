package com.magic.officeapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import com.magic.officeapp.ui.component.BottomNavigationBar
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.screen.*
import com.magic.officeapp.ui.screen.employee.*
import com.magic.officeapp.ui.theme.Grey700
import com.magic.officeapp.ui.theme.OfficeAppTheme
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.utils.hasLocationPermission
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
@SuppressLint("MissingPermission")
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "FlowOperatorInvokedInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OfficeAppTheme {
                Scaffold {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    val authViewModel = hiltViewModel<AuthViewModel>(this)
                    val user by authViewModel.userData.collectAsState()
                    val isLogged by authViewModel.isLogged.collectAsState()
                    val loading by authViewModel.loading.collectAsState()

                    fun insertAttendance() {
                        if (user?.job_role != "HR") {
                            if (hasLocationPermission()) {
                                val fusedLocationClient =
                                    LocationServices.getFusedLocationProviderClient(this)
                                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                                    if (location != null) {
                                        val lat = location.latitude
                                        val long = location.longitude

                                    } else {
                                        Toast.makeText(
                                            this, "Please turn on your location", Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            } else {
                                requestPermissions(
                                    arrayOf(
                                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                                    ), 1
                                )
                            }


                        }
                    }


                    Scaffold(
                        bottomBar = {
                            if (currentRoute == Screen.HomeScreen.route || currentRoute == Screen.AnnouncementScreen.route) {
                                BottomNavigationBar(navController = navController)
                            }
                        },
                        floatingActionButton = {
                            if (user?.job_role != "HR") {
                                if (currentRoute == Screen.HomeScreen.route || currentRoute == Screen.AnnouncementScreen.route) {
                                    FloatingActionButton(
                                        onClick = {
                                            insertAttendance()
                                        },
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
                            }
                        },
                        floatingActionButtonPosition = FabPosition.Center,
                        isFloatingActionButtonDocked = true,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = if (loading) Screen.LoadingScreen.route else if (isLogged) Screen.HomeScreen.route else Screen.SplashScreen.route
                        ) {
                            composable(Screen.HomeScreen.route) {
                                HomeScreen(navController = navController)
                            }

                            composable(Screen.LoadingScreen.route) {
                                LoadingScreen()
                            }

                            composable(Screen.SplashScreen.route) {
                                SplashScreen(navController = navController)
                            }
                            composable(Screen.LoginScreen.route) {
                                LoginScreen(navController = navController)
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
