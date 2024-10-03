package com.wooyj.picsum.feature.main.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wooyj.picsum.feature.main.ui.navigation.PicSumNavHost
import com.wooyj.picsum.feature.main.ui.navigation.Screen
import com.wooyj.picsum.ui.scheme.BottomNavigationScheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(null)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentBottomNavigationScheme by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry?.destination?.route?.let { route ->
                BottomNavigationScheme.entries.find { it.route == route }
            } ?: run {
                BottomNavigationScheme.List
            }
        }
    }

    LaunchedEffect(key1 = effect) {
        val navigate = effect?.screen?.route ?: return@LaunchedEffect
        navController.navigate(navigate)
    }

    Scaffold(
        bottomBar = {
            // Bottom Navigation Bar
            when (uiState) {
                is MainUIState.Success -> {
                    BottomNavigationView(
                        list = (uiState as MainUIState.Success).bottomNavigationSchemes,
                        currentBottomNavigationScheme = currentBottomNavigationScheme,
                        onClick = { item ->
                            when (item.route) {
                                Screen.List.route -> {
                                    viewModel.onEvent(MainEvent.OnListClickEvent)
                                }

                                Screen.FavoriteList.route -> {
                                    viewModel.onEvent(MainEvent.OnFavClickEvent)
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
                modifier = modifier.padding(innerPadding),
                navController = navController,
                onError = { error ->
                    viewModel.onEvent(
                        MainEvent.OnReviceErrorEvent(error),
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
                        text = stringResource(id = item.title),
                        fontSize = 11.sp,
                    )
                },
                onClick = {
                    onClick(item)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.route,
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
