package com.magic.officeapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
) {

    object Home : BottomNavigationItem(
        route = Screen.HomeScreen.route,
        title = "Home",
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home
    )

    object Attendance : BottomNavigationItem(
        route = Screen.AttendanceScreen.route,
        title = "Attendance",
        icon = Icons.Outlined.Search,
        selectedIcon = Icons.Filled.Search
    )

    object Announcement : BottomNavigationItem(
        route = Screen.AnnouncementScreen.route,
        title = "Announcement",
        icon = Icons.Outlined.Notifications,
        selectedIcon = Icons.Filled.Notifications
    )

    object Profile : BottomNavigationItem(
        route = Screen.ProfileScreen.route,
        title = "Profile",
        icon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person
    )

}