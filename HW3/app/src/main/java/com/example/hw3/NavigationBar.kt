package com.example.hw3

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class Destination(val route: String) {
    MAIN("main"),
    USER("user")
}

@Composable
fun NavigationBar(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val user = UserViewModel(PreferenceDataStore(LocalContext.current))
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                NavigationBarItem(
                    selected = navBackStackEntry?.destination?.route == Destination.MAIN.route,
                    onClick = {
                        navController.navigate(route = Destination.MAIN.route) {
                            popUpTo(Destination.MAIN.route) { inclusive = true }
                        }
                    },
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = navBackStackEntry?.destination?.route == Destination.USER.route,
                    onClick = {
                        navController.navigate(route = Destination.USER.route) {
                            popUpTo(Destination.USER.route) { inclusive = true }
                        }
                    },
                    icon = {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    },
                    label = { Text("Settings") }
                )
            }
        }
    ) { contentPadding ->
        NavHost(navController, startDestination = Destination.MAIN.route, modifier = Modifier.padding(contentPadding)) {
            composable(Destination.MAIN.route) { backStackEntry ->
                // val param: Test = backStackEntry.toRoute()
                MainScreen(user)
            }
            composable(Destination.USER.route) { backStackEntry ->
                UserInfoScreen(user)
            }
        }
    }
}