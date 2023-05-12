package com.magic.officeapp.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.magic.officeapp.ui.navigation.BottomNavigationItem

@Composable
fun BottomNavigationBar(
    navController: NavController, isHR: Boolean = false
) {
    val primaryColor = Color.Black

    val items = if (isHR) {
        listOf(
            BottomNavigationItem.HRHome,
            BottomNavigationItem.HRAnnouncement,
        )
    } else {
        listOf(
            BottomNavigationItem.Home,
            BottomNavigationItem.Attendance,
            BottomNavigationItem.Announcement,
        )
    }

    BottomNavigation(backgroundColor = Color.White, contentColor = primaryColor) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEachIndexed { index, item ->
            if (index != 1 || isHR) {
                BottomNavigationItem(icon = {
                    Icon(imageVector = currentRoute?.let {
                        if (currentRoute == item.route) item.selectedIcon else item.icon
                    } ?: item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp))
                }, selected = currentRoute == item.route, onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                }, label = {
                    Text(text = item.title)
                })
            }
        }
    }
}
