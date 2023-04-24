package com.magic.officeapp.ui.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object AttendanceScreen : Screen("attendance_screen")
    object AnnouncementScreen : Screen("announcement_screen")
    object ProfileScreen : Screen("profile_screen")

    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
}