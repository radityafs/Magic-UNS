package com.magic.officeapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MenuItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object RequestUser : MenuItem(
        route = Screen.RequestScreen.route,
        title = "Request",
        icon = Icons.Outlined.Home,
    )

    object AttendanceUser : MenuItem(
        route = Screen.AttendanceScreen.route,
        title = "Attendance",
        icon = Icons.Outlined.Search,
    )

    object PayrollUser : MenuItem(
        route = Screen.PayrollScreen.route,
        title = "Payroll",
        icon = Icons.Outlined.Notifications,
    )

    object AnnouncementAdmin : MenuItem(
        route = Screen.AnnouncementScreen.route,
        title = "Announcement",
        icon = Icons.Outlined.Person,
    )

    object AttendanceAdmin : MenuItem(
        route = Screen.AttendanceScreen.route,
        title = "Attendance",
        icon = Icons.Outlined.Search,
    )

    object PayrollAdmin : MenuItem(
        route = Screen.PayrollScreen.route,
        title = "Payroll",
        icon = Icons.Outlined.Notifications,
    )

}