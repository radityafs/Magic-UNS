package com.magic.officeapp

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import com.magic.officeapp.data.model.response.AttendanceResponse
import com.magic.officeapp.data.model.response.LocationResponse
import com.magic.officeapp.ui.component.BottomNavigationBar
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.screen.*
import com.magic.officeapp.ui.screen.employee.*
import com.magic.officeapp.ui.screen.hr.HrAnnouncementFormScreen
import com.magic.officeapp.ui.screen.hr.HrAnnouncementScreen
import com.magic.officeapp.ui.screen.hr.HrEmployeeListScreen
import com.magic.officeapp.ui.screen.hr.HrHomeScreen
import com.magic.officeapp.ui.theme.Grey700
import com.magic.officeapp.ui.theme.OfficeAppTheme
import com.magic.officeapp.ui.viewmodel.AttendanceViewModel
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.utils.constants.Result
import com.magic.officeapp.utils.hasLocationPermission
import dagger.hilt.android.AndroidEntryPoint
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
                    val attendanceViewModel = hiltViewModel<AttendanceViewModel>(this)

                    val user by authViewModel.userData.collectAsState()
                    val isLogged by authViewModel.isLogged.collectAsState()
                    val todayAttendance by attendanceViewModel.todayAttendance.collectAsState()
                    val officeLocation by attendanceViewModel.location.collectAsState()
                    val loadingAuthentication by authViewModel.loading.collectAsState()
                    val loadingAttendance by attendanceViewModel.loading.collectAsState()

                    if (isLogged && todayAttendance == Result.Empty) {
                        attendanceViewModel.getAttendanceToday(user?.id.toString())
                    }

                    fun startDestination() : String {
                        if (loadingAuthentication || loadingAttendance) {
                            Screen.LoadingScreen.route
                        }

                        return if (isLogged) {
                            if (user?.role?.name == "HR") {
                                Screen.HRHomeScreen.route
                            } else {
                                Screen.HomeScreen.route
                            }
                        } else {
                            Screen.SplashScreen.route
                        }
                    }

                    fun insertAttendance() {
                        if (user?.role?.name != "HR") {
                            if (loadingAttendance) {
                                Toast.makeText(
                                    this, "Please wait", Toast.LENGTH_SHORT
                                ).show()
                                return
                            }

                            val todayAttendance =
                                todayAttendance as Result.Success<AttendanceResponse>
                            val isCheckIn = todayAttendance.data.data?.isNotEmpty()!!

                            if (isCheckIn) {
                                Toast.makeText(
                                    this, "You have checked in today", Toast.LENGTH_SHORT
                                ).show()
                                return
                            }

                            if (hasLocationPermission()) {
                                val fusedLocationClient =
                                    LocationServices.getFusedLocationProviderClient(this)
                                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                                    if (location != null) {
                                        val dataOfficeLocation =
                                            officeLocation as Result.Success<LocationResponse>
                                        val officeLocation = Location(
                                            "Office Location"
                                        )

                                        officeLocation.latitude =
                                            dataOfficeLocation.data.data?.attributes?.latitude?.toDouble()!!
                                        officeLocation.longitude =
                                            dataOfficeLocation.data.data?.attributes?.longitude?.toDouble()!!

                                        val distance =
                                            location.distanceTo(officeLocation) // in meters

                                        if (distance > 100) {
                                            Toast.makeText(
                                                this,
                                                "You are not in the office area",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                            return@addOnSuccessListener
                                        }

                                        attendanceViewModel.insertAttendance(
                                            user?.id.toString(),
                                            location.latitude.toString(),
                                            location.longitude.toString()
                                        )

                                        Toast.makeText(
                                            this, "Check in success", Toast.LENGTH_SHORT
                                        ).show()
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

                            if(currentRoute == Screen.HRHomeScreen.route) {
                                BottomNavigationBar(navController = navController)
                            }
                        },
                        floatingActionButton = {
                            if (user?.role?.name != "HR") {
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
                            startDestination = startDestination()
                        ) {


                            composable(Screen.SplashScreen.route) {
                                SplashScreen(navController = navController)
                            }

                            composable(Screen.LoadingScreen.route) {
                                LoadingScreen()
                            }


                            composable(Screen.LoginScreen.route) {
                                LoginScreen(navController = navController)
                            }

                            // USER
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


                            // HR
                            composable(Screen.HRHomeScreen.route) {
                                HrHomeScreen(navController = navController)
                            }

                            composable(Screen.HrAnnouncementScreen.route) {
                                HrAnnouncementScreen(navController = navController)
                            }

                            composable(Screen.HrAnnouncementFormScreen.route) {
                                HrAnnouncementFormScreen(navController = navController)
                            }

                            composable(Screen.HREmployeeListScreen.route) {
                                HrEmployeeListScreen(navController = navController)
                            }

                            composable(Screen.HRAddEmployeeScreen.route) {
                                HrAddEmployeeScreen(navController = navController)
                            }

                        }
                    }
                }
            }
        }
    }
}
