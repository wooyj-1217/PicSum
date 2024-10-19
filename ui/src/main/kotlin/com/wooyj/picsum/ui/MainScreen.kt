package com.wooyj.picsum.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wooyj.picsum.feature.favorite.ui.scheme.favoriteBottomNavigationScheme
import com.wooyj.picsum.feature.favorite.ui.scheme.favoriteScheme
import com.wooyj.picsum.feature.list.ui.scheme.listBottomNavigationScheme
import com.wooyj.picsum.feature.list.ui.scheme.listScheme
import com.wooyj.picsum.feature.setting.ui.scheme.settingBottomNavigationScheme
import com.wooyj.picsum.feature.setting.ui.scheme.settingScheme
import com.wooyj.picsum.ui.navigation.PicSumNavHost
import com.wooyj.picsum.ui.scheme.BottomNavigationScheme
import timber.log.Timber

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    bottomUiScheme: List<BottomNavigationScheme>,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(null)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentBottomNavigationScheme by remember(navBackStackEntry) {
        derivedStateOf {
//            navBackStackEntry?.destination?.route?.let { route ->
//                BottomNavigationScheme.entries.find { it.route == route }
//            } ?: run {
//                BottomNavigationScheme.List
//            }
            navBackStackEntry?.destination?.route?.let { route ->
                bottomUiScheme.find { it.scheme.route == route }
            } ?: bottomUiScheme.firstOrNull() ?: run {
                Timber.d("currentBottomNavigationScheme is null value")
                throw IllegalStateException("currentBottomNavigationScheme is null value")
            }
        }
    }

    LaunchedEffect(key1 = effect) {
        effect?.scheme?.route?.let { route ->
            Timber.d("effect route : $route")
            navController.navigate(route)
        } ?: run {
            Timber.d("effect is null value : $effect")
        }
    }

    Scaffold(
        bottomBar = {
            // Bottom Navigation Bar
            when (uiState) {
                is MainUIState.Success -> {
                    BottomNavigationView(
                        list = bottomUiScheme,
                        currentBottomNavigationScheme = currentBottomNavigationScheme,
                        onClick = { item ->
                            when (item.scheme.route) {
                                listScheme.route -> {
                                    viewModel.onEvent(MainEvent.OnListClickEvent)
                                }

                                favoriteScheme.route -> {
                                    viewModel.onEvent(MainEvent.OnFavClickEvent)
                                }

                                settingScheme.route -> {
                                    viewModel.onEvent(MainEvent.OnSettingClickEvent)
                                }

                                else -> Unit
                            }
                        },
                    )
                }

                else -> Unit
            }
        },
        content = { innerPadding ->
            // 여기의 UIState는 Screen
            PicSumNavHost(
                modifier = modifier.padding(innerPadding),
                navController = navController,
                onError = { error ->
                    viewModel.onEvent(
                        MainEvent.OnReceiveErrorEvent(error),
                    )
                },
            )
        },
    )
}

@Composable
fun BottomNavigationView(
    modifier: Modifier = Modifier,
    list: List<BottomNavigationScheme>,
    currentBottomNavigationScheme: BottomNavigationScheme,
    onClick: (BottomNavigationScheme) -> Unit,
) {
    BottomNavigation(
        modifier = modifier,
        elevation = 0.dp,
        backgroundColor = Color.White,
    ) {
        list.forEach { item ->
            BottomNavigationItem(
                selected = item == currentBottomNavigationScheme,
                label = {
                    Text(
                        text = item.title,
                        fontSize = 11.sp,
                    )
                },
                onClick = {
                    onClick(item)
                },
                icon = {
                    item.icon?.invoke()
                },
            )
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    val bottomUiScheme =
        listOf(
            listBottomNavigationScheme,
            favoriteBottomNavigationScheme,
            settingBottomNavigationScheme,
        )
    MainScreen(bottomUiScheme = bottomUiScheme)
}
