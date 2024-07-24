package com.wooyj.picsum.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wooyj.picsum.ui.screen.detail.DetailScreen
import com.wooyj.picsum.ui.screen.detail.viewmodel.FavoriteViewModel
import com.wooyj.picsum.ui.screen.detail.viewmodel.ListDetailViewModel
import com.wooyj.picsum.ui.screen.list.ListScreen
import com.wooyj.picsum.ui.screen.setting.SettingScreen

@Composable
fun PicSumNavHost(
    modifier: Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.List.route,
    ) {
        composable(route = Screen.List.route) {
            ListScreen(
                onNextNavigation = {
                    navController.navigate(Screen.Detail.route)
                },
            )
        }
        composable(route = Screen.Detail.route) {
            DetailScreen(
                viewModel = ListDetailViewModel(),
            )
        }
        composable(route = Screen.Favorite.route) {
            DetailScreen(
                viewModel = FavoriteViewModel(),
            )
        }
        composable(route = Screen.Setting.route) {
            SettingScreen()
        }
    }
}
