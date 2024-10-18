package com.wooyj.picsum.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import com.wooyj.picsum.feature.detail.ui.DetailScreen
import com.wooyj.picsum.feature.detail.ui.scheme.detailScheme
import com.wooyj.picsum.feature.favdetail.ui.FavoriteDetailScreen
import com.wooyj.picsum.feature.favdetail.ui.scheme.favDetailScheme
import com.wooyj.picsum.feature.favorite.ui.FavoriteListScreen
import com.wooyj.picsum.feature.favorite.ui.scheme.favoriteScheme
import com.wooyj.picsum.feature.list.ui.ListScreen
import com.wooyj.picsum.feature.list.ui.scheme.listScheme
import com.wooyj.picsum.feature.setting.ui.SettingScreen
import com.wooyj.picsum.feature.setting.ui.scheme.settingScheme
import timber.log.Timber

@Composable
fun PicSumNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onError: (String) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = "listGraph",
    ) {
        // 1
        // favorite, favoriteDetail
        // list, detail
        // setting

        // 2
        // list, favorite, setting
        // favoriteDetail
        // detail

        navigation(
            route = "listGraph",
            startDestination = listScheme.route,
        ) {
            logComposable(
                route = listScheme.route,
            ) {
                ListScreen(
                    onNextNavigation = { id ->
                        navController.navigate(route = "${detailScheme.route}/$id")
                    },
                )
            }
            logComposable(
                route = "${detailScheme.route}/{photoId}",
                arguments =
                    listOf(
                        navArgument("photoId") { type = NavType.StringType },
                    ),
            ) {
                DetailScreen()
            }
        }
        navigation(
            route = "favoriteGraph",
            startDestination = favoriteScheme.route,
        ) {
            logComposable(
                route = favoriteScheme.route,
            ) {
                FavoriteListScreen(
                    onNextNavigation = { id ->
                        navController.navigate(route = "${favDetailScheme.route}/$id")
                    },
                )
            }
            logComposable(
                route = "${favDetailScheme.route}/{photoId}",
                arguments =
                    listOf(
                        navArgument("photoId") { type = NavType.StringType },
                    ),
            ) {
                FavoriteDetailScreen()
            }
        }
        navigation(
            route = "settingGraph",
            startDestination = settingScheme.route,
        ) {
            logComposable(
                route = settingScheme.route,
            ) {
                SettingScreen()
            }
        }

//        logComposable(
//            route = Screen.Error.route,
//        )
//        logComposable(route = Screen.List.route) {
//            ListScreen(
//                onNextNavigation = { id ->
//                    navController.navigate(route = Screen.Detail.setPhotoId(id))
//                },
//            )
//        }
//        logComposable(
//            route = Screen.Detail.route,
//            arguments =
//                listOf(
//                    navArgument("photoId") { type = NavType.StringType },
//                ),
//        ) {
//            DetailScreen()
//        }
//        logComposable(route = Screen.FavoriteList.route) {
//            FavoriteListScreen(
//                onNextNavigation = { id ->
//                    navController.navigate(route = Screen.FavoriteDetail.setPhotoId(id))
//                },
//            )
//        }
//        logComposable(
//            route = Screen.FavoriteDetail.route,
//            arguments =
//                listOf(
//                    navArgument("photoId") { type = NavType.StringType },
//                ),
//        ) {
//            FavoriteDetailScreen()
//        }
//        logComposable(route = Screen.Setting.route) {
//            SettingScreen()
//        }
    }
}

private fun NavGraphBuilder.logComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
    ) { backStackEntry ->
        val context = LocalContext.current
        Timber.d("Route: $route")

        // Firebase 초기화 확인
        val myTrace =
            if (FirebaseApp.getApps(context).isNotEmpty()) {
                Firebase.performance.newTrace(route).apply { start() }
            } else {
                null
            }

        content(backStackEntry)

        // Firebase Performance Trace 종료
        myTrace?.stop()
    }
}
