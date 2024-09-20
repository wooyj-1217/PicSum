package com.wooyj.picsum.feature.main.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import com.wooyj.picsum.feature.detail.ui.DetailScreen
import com.wooyj.picsum.feature.favorite.ui.FavoriteListScreen
import com.wooyj.picsum.feature.list.ui.ListScreen
import com.wooyj.picsum.ui.screen.setting.SettingScreen
import timber.log.Timber

@Composable
fun PicSumNavHost(
    modifier: Modifier,
    navController: NavHostController,
    onError: (String) -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.List.route,
    ) {
        logComposable(route = Screen.List.route) {
            ListScreen(
                onNextNavigation = { id ->
                    navController.navigate(route = Screen.Detail.setPhotoId(id))
                },
            )
        }
        logComposable(
            route = Screen.Detail.route,
            arguments =
                listOf(
                    navArgument("photoId") { type = NavType.StringType },
                ),
        ) {
            DetailScreen()
        }
        logComposable(route = Screen.FavoriteList.route) {
            FavoriteListScreen(
                onNextNavigation = { id ->
                    navController.navigate(route = Screen.FavoriteDetail.setPhotoId(id))
                },
            )
        }
        logComposable(
            route = Screen.FavoriteDetail.route,
            arguments =
                listOf(
                    navArgument("photoId") { type = NavType.StringType },
                ),
        ) {
            DetailScreen()
        }
        logComposable(route = Screen.Setting.route) {
            SettingScreen()
        }
    }
}

private fun NavGraphBuilder.logComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable () -> Unit,
) {
    composable(
        route = route,
        arguments = arguments,
    ) { backStackEntry ->
        Timber.d("Route: $route")
        // NavBackStackEntry에서 전달된 인수 값을 가져와서 Timber로 로그 출력
        val myTrace = Firebase.performance.newTrace(route)
        myTrace.start()
        content()
        myTrace.stop()
    }
}
