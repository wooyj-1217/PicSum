package com.wooyj.picsum.ui.scheme

data class NavigationScheme(
    val route: String,
)

enum class RouteContract(
    val route: String,
) {
    Main("main"),
    List("list"),
    Favorite("favorite"),
    Setting("setting"),
}
