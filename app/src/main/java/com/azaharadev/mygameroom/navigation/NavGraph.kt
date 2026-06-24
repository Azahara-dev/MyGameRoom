package com.azaharadev.mygameroom.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.azaharadev.mygameroom.ui.screens.explore.ExploreScreen
import com.azaharadev.mygameroom.ui.screens.favorites.FavoritesScreen
import com.azaharadev.mygameroom.ui.screens.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {
        composable(Routes.HOME) { HomeScreen(onNavigateToExplore = { navController.navigate(Routes.EXPLORE) }) }
        composable(Routes.EXPLORE) { ExploreScreen() }
        composable(Routes.FAVORITES) { FavoritesScreen() }
    }
}