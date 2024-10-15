package com.wooyj.picsum.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import com.wooyj.picsum.feature.favorite.ui.scheme.favoriteBottomNavigationScheme
import com.wooyj.picsum.feature.list.ui.scheme.listBottomNavigationScheme
import com.wooyj.picsum.feature.setting.ui.scheme.settingBottomNavigationScheme
import com.wooyj.picsum.ui.theme.PicSumTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bottomUiScheme =
                listOf(
                    listBottomNavigationScheme,
                    favoriteBottomNavigationScheme,
                    settingBottomNavigationScheme,
                )

            PicSumTheme {
                CompositionLocalProvider(
                    provideMainViewModelFactory { mainViewModel() }, //(전역에서 쓰는 viewModel)
                ) {
                    MainScreen()

//                    NavHost(
//                        navController = navController,
//                        startDestination = listScheme.route,
//                    ) {
                        // 1
                        // favorite, favoriteDetail
                        // list, detail
                        // setting

                        // 2
                        // list, favorite, setting
                        // favoriteDetail
                        // detail

//                        navigation(
//                            route = listScheme.route,
//                            startDestination = listScheme.route,
//                        ) {
//                            composable(
//                                route = listScheme.route
//                            ) {
//                                ListScreen(
//                                    onNextNavigation = { id ->
//                                        navController.navigate(route = detailScheme.route)
//                                    }
//                                )
//                            }
//                            composable(
//                                route = detailScheme.route
//                            ) {
//                                DetailScreen()
//                            }
//                        }
//                        navigation(
//                            route = favoriteScheme.route,
//                            startDestination = favoriteScheme.route,
//                        ) {
//                            composable(
//                                route = favDetailScheme.route
//                            ) {
//                                FavoriteDetailScreen()
//                            }
//                        }
//                        navigation(
//                            route = settingScheme.route,
//                            startDestination = settingScheme.route,
//                        ) {
//                        }

//                        mainRoute()
//                        navigation(
//                            route = detailScheme.main,
//                            startDestination = detailScheme.route,
//                        ) {
//                            composable(
//                                route = detailScheme.route,
//                            ) {
//                                DetailScreen()
//                            }
//                        }

                }
            }
        }
    }
}

// 1. Scheme 별도로 만들어야 한다.
// -> Route가 있어야 부르고, 호출하고, 세팅하고
// 2. Ui (Activity)모듈은 모든 Feature를 알아야한다
// 3. feature끼리는 서로 모른다. 관심사가 아닙니다
// 4. Activity에서는 생각보다 많은 것을 세팅하고 다룬다

// 1. usecase는 flow!!!
// 2. flow는 다음과 같이 사용한다!!!!!!
/*
private fun load(currentId: String) {
            detailUseCase(currentId = currentId)
                .onStart {
                    _uiState.value = DetailUIState.Loading
                    Timber.d("detailUseCase : onStart")
                }.onEach { item ->
                    if (_uiState.value !is DetailUIState.Success) {
                        _uiState.value = DetailUIState.Success(item.toDetailTypeUI())
                        Timber.d("insert: ${item.toDetailTypeUI()}")
                    } else {
                        _uiState.update {
                            (it as DetailUIState.Success).copy(ui = item.toDetailTypeUI())
                        }
                        Timber.d("update: ${item.toDetailTypeUI()}")
                    }
                }.catch {
                    Timber.e("detailUseCase : catch", it)
                }.launchIn(viewModelScope)
        }
 */

// 3. Use Case 빈혈클래스인 UseCase인 경우는 다음과 같이 Flow로 변환해서 바꾼다!!
// @Reusable
// class RemoveFavoriteNotVisibleUseCase
// @Inject
// constructor(
//    private val usecase: RemoveFavoriteNotVisibleUseCase,
// ) {
//    operator fun invoke() = flow { emit(usecase()) }
// }
//
