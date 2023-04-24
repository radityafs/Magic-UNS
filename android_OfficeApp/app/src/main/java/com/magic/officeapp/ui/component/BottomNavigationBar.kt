package com.magic.officeapp.ui.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.magic.officeapp.ui.navigation.BottomNavigationItem

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val primaryColor = Color.Black

    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Attendance,
        BottomNavigationItem.Announcement,
        BottomNavigationItem.Profile
    )

    BottomNavigation(backgroundColor = Color.White, contentColor = primaryColor) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = currentRoute?.let {
                            if (currentRoute == item.route) item.selectedIcon else item.icon
                        } ?: item.icon,
                        contentDescription = item.title
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    if (
                        currentRoute != item.route
                    ) navController.navigate(
                        item.route
                    )
                }
            )
        }
    }
}
