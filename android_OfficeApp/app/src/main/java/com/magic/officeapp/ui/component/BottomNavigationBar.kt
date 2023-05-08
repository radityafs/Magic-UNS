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
    navController: NavController
) {
    val primaryColor = Color.Black

    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Attendance,
        BottomNavigationItem.Announcement,
    )

    BottomNavigation(backgroundColor = Color.White, contentColor = primaryColor) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEachIndexed { index, item ->
            if (index != 1) {
                BottomNavigationItem(
                    icon = {
                        Icon(imageVector = currentRoute?.let {
                            if (currentRoute == item.route) item.selectedIcon else item.icon
                        } ?: item.icon, contentDescription = item.title, modifier = Modifier.size(24.dp))
                    },
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    label = {
                        Text(text = item.title)
                    }
                    )
            }
        }
    }
}
