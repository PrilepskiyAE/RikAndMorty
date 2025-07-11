package com.prilepskiy.rikandmorty.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.prilepskiy.presentation.detailScreen.ui.DetailScreen
import com.prilepskiy.prisentation.mainScreen.ui.screen.MainScreen

const val mainRoute = "main_route"
const val detailRoute = "detail_route"
fun NavGraphBuilder.mainNavigate(goToUser: (Int) -> Unit, popBack: () -> Unit) {
    composable(route = mainRoute) {
        MainScreen(goToUser = goToUser)
    }
    composable(
        route = "$detailRoute/{userId}",
        arguments = listOf(navArgument("userId") { type = NavType.IntType })
    ) { navBackStack ->
        DetailScreen(navBackStack.arguments?.getInt("userId") ?: 0) {
            popBack.invoke()
        }
    }
}

fun NavController.navigationToDetail(userId: Int) {
    this.navigate("$detailRoute/${userId}")
}