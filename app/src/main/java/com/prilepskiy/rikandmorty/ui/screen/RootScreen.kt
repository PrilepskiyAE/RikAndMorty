package com.prilepskiy.rikandmorty.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.prilepskiy.rikandmorty.navigation.mainNavigate
import com.prilepskiy.rikandmorty.navigation.mainRoute
import com.prilepskiy.rikandmorty.navigation.navigationToDetail


@Composable
fun RootScreen(
    modifier: Modifier = Modifier,
    startDestination: String = mainRoute,
) {
    val rootNavController: NavHostController = rememberNavController()
    NavHost(
        navController = rootNavController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        mainNavigate(
            goToUser = { userId ->
                rootNavController.navigationToDetail(userId)
            },
            popBack = { rootNavController.popBackStack() })
    }
}