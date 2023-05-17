package com.magic.officeapp

import android.annotation.SuppressLint
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import com.magic.officeapp.ui.component.Dialog
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.screen.*
import com.magic.officeapp.ui.screen.employee.*
import com.magic.officeapp.ui.screen.hr.HrAnnouncementFormScreen
import com.magic.officeapp.ui.screen.hr.HrAnnouncementScreen
import com.magic.officeapp.ui.screen.hr.HrEmployeeListScreen
import com.magic.officeapp.ui.screen.hr.HrHomeScreen
import com.magic.officeapp.ui.screen.hr.HrRequestDetailScreen
import com.magic.officeapp.ui.screen.hr.HrRequestScreen
import com.magic.officeapp.ui.theme.*
import com.magic.officeapp.ui.viewmodel.AttendanceViewModel
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.utils.constants.DialogItem
import com.magic.officeapp.utils.constants.Result
import com.magic.officeapp.utils.hasLocationPermission
import com.magic.officeapp.utils.isHoliday
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
@SuppressLint("MissingPermission")
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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
                    val showDialog = remember { mutableStateOf(false) }
                    val dialogItem = remember { mutableStateOf(DialogItem()) }

                    if (isLogged && user?.role?.name != "HR" && todayAttendance == Result.Empty) {
                        attendanceViewModel.getAttendanceToday(user?.id.toString())
                    }

                    fun startDestination(): String {
                        if (loadingAuthentication) {
                            return Screen.LoadingScreen.route
                        }

                        return if (isLogged) {
                            if (user?.role?.name == "HR") {
                                Screen.HRHomeScreen.route
                            } else {
                                Screen.HomeScreen.route
                            }
                        } else {
                            return Screen.SplashScreen.route
                        }
                    }

                    fun insertAttendance() {
                        if (isLogged && user?.role?.name != "HR") {
                            if (loadingAttendance) {
                                Toast.makeText(
                                    this, "Please wait", Toast.LENGTH_SHORT
                                ).show()
                                return
                            }

                            var isCheckIn = false
                            if (todayAttendance is Result.Success) {
                                isCheckIn =
                                    (todayAttendance as Result.Success<AttendanceResponse>).data.data?.size!! > 0
                            } else {
                                Toast.makeText(
                                    this, "Failed to get attendance data", Toast.LENGTH_SHORT
                                ).show()
                                return
                            }

                            if (isCheckIn) {
                                dialogItem.value = DialogItem(
                                    backgroundButton = Grey800,
                                    icon = R.drawable.state_leave,
                                    title = "Check Out",
                                    message = "Are you sure want to check out?",
                                    onConfirmAction = {
                                        showDialog.value = false
                                    },
                                    onCancelAction = {
                                        showDialog.value = false
                                    },
                                    iconBackgroundColor = Grey100,
                                )
                                showDialog.value = true
                                return
                            }

                            if (hasLocationPermission()) {
                                val fusedLocationClient =
                                    LocationServices.getFusedLocationProviderClient(this)
                                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                                    if (location != null) {
                                        val officeLocationLoc = Location(
                                            "Office Location"
                                        )

                                        if (officeLocation is Result.Success) {
                                            val data =
                                                (officeLocation as Result.Success<LocationResponse>)
                                            officeLocationLoc.latitude =
                                                data.data.data?.attributes?.latitude?.toDouble()!!
                                            officeLocationLoc.longitude =
                                                data.data.data?.attributes?.longitude?.toDouble()!!
                                        } else {
                                            Toast.makeText(
                                                this,
                                                "Failed to get office location",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            return@addOnSuccessListener
                                        }

                                        val distance =
                                            location.distanceTo(officeLocationLoc).toDouble()

                                        if (distance > 100) {
                                            dialogItem.value = DialogItem(
                                                backgroundButton = Red600,
                                                icon = R.drawable.state_location,
                                                title = "Location does not match",
                                                message = "Your location is not what the app requires. Please check your location settings again or try again later.",
                                                onConfirmAction = {
                                                    showDialog.value = false
                                                },
                                                iconBackgroundColor = Red100,
                                            )
                                            showDialog.value = true
                                            return@addOnSuccessListener
                                        }

                                        attendanceViewModel.insertAttendance(
                                            user?.id.toString(),
                                            location.latitude.toString(),
                                            location.longitude.toString()
                                        )

                                        dialogItem.value = DialogItem(
                                            backgroundButton = Green700,
                                            icon = R.drawable.state_success,
                                            title = "Check In Success",
                                            message = "Let's work together to achieve our goals and make a positive impact. We got this!",
                                            onConfirmAction = {
                                                showDialog.value = false
                                            },
                                            iconBackgroundColor = Green100,
                                        )
                                        showDialog.value = true
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

                            if (currentRoute == Screen.HRHomeScreen.route) {
                                BottomNavigationBar(navController = navController, isHR = true)
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

                        Dialog(
                            show = showDialog.value,
                            title = dialogItem.value.title,
                            message = dialogItem.value.message,
                            onDismissAction = dialogItem.value.onDismissAction,
                            icon = dialogItem.value.icon,
                            iconBackgroundColor = dialogItem.value.iconBackgroundColor,
                            onConfirmAction = dialogItem.value.onConfirmAction,
                            backgroundButton = dialogItem.value.backgroundButton,
                            onCancelAction = dialogItem.value.onCancelAction
                        )

                        NavHost(
                            navController = navController, startDestination = startDestination()
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

                            composable(Screen.RequestDetailScreen.route+"/{id}") {
                                val id = it.arguments?.getString("id")?.toInt()
                                RequestDetailScreen(navController = navController,id=id!!)
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

                            composable(Screen.HrRequestScreen.route) {
                                HrRequestScreen(navController = navController)
                            }

                            composable(Screen.HrRequestDetailScreen.route + "/{id}") {backStackEntry ->
                                val id = backStackEntry.arguments?.getString("id")?.toInt()
                                HrRequestDetailScreen(navController = navController, id = id!!)
                            }
                        }
                    }
                }
            }
        }
    }
}
