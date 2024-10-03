package com.wooyj.picsum.feature.main.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation

fun NavGraphBuilder.mainRoute() {
    navigation(
        startDestination = MainDestination.Home.path,
        route = MainDestination.Main.path,
    ) {
        composable(MainDestination.Home.path) {
            HomeScreen()
        }
        composable(MainDestination.Contacts.path) {
            ContactsScreen()
        }
        composable(MainDestination.Calendar.path) {
            CalendarScreen()
        }
    }
}
