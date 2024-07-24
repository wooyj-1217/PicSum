package com.wooyj.picsum.ui.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wooyj.picsum.ui.navigation.PicSumNavHost
import com.wooyj.picsum.ui.navigation.Screen
import com.wooyj.picsum.ui.screen.main.model.BottomNavigationItem

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(null)

    LaunchedEffect(key1 = effect) {
        when (effect) {
            is MainEffect.NavigateToList -> navController.navigate(Screen.List.route)
            is MainEffect.NavigateToFavorite -> navController.navigate(Screen.Favorite.route)
            is MainEffect.NavigateToSetting -> navController.navigate(Screen.Setting.route)
            else -> Unit
        }
    }

    Scaffold(
        bottomBar = {
            // Bottom Navigation Bar
            when (uiState) {
                is MainUIState.Success -> {
                    BottomNavigationView(
                        list = (uiState as MainUIState.Success).bottomNavigationItems,
                        onClick = { item ->
                            when (item.route) {
                                Screen.List.route -> {
                                    viewModel.onEvent(MainEvent.OnListClickEvent)
                                }

                                Screen.Favorite.route -> {
                                    viewModel.onEvent(MainEvent.OnLikeClickEvent)
                                }

                                Screen.Setting.route -> {
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
                modifier = Modifier.padding(innerPadding),
                navController = navController,
            )
        },
    )
}

@Composable
fun BottomNavigationView(
    list: List<BottomNavigationItem>,
    onClick: (BottomNavigationItem) -> Unit,
) {
    BottomNavigation(
        elevation = 0.dp,
        backgroundColor = Color.White,
    ) {
        list.forEach { item ->
            BottomNavigationItem(
                label = {
                    Text(
                        text = item.title,
                        fontSize = 11.sp,
                    )
                },
                selected = item.selected,
                onClick = {
                    onClick(item)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "",
                    )
                },
            )
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
