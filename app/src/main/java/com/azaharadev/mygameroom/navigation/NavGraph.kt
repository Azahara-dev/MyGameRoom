package com.azaharadev.mygameroom.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.azaharadev.mygameroom.ui.screens.explore.ExploreScreen
import com.azaharadev.mygameroom.ui.screens.favorites.FavoritesScreen
import com.azaharadev.mygameroom.ui.screens.home.HomeScreen
import com.azaharadev.mygameroom.viewmodel.GamesViewModel

@Composable
fun NavGraph(navController: NavHostController, gamesViewModel: GamesViewModel, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {
        composable(Routes.HOME) { HomeScreen(onNavigateToExplore = { navController.navigate(Routes.EXPLORE) }, gamesViewModel = gamesViewModel) }
        composable(Routes.EXPLORE) { ExploreScreen(gamesViewModel = gamesViewModel) }
        composable(Routes.FAVORITES) { FavoritesScreen(gamesViewModel = gamesViewModel) }
    }
}