package com.kodarisu.mygameroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kodarisu.mygameroom.navigation.BottomNavBar
import com.kodarisu.mygameroom.navigation.NavGraph
import com.kodarisu.mygameroom.navigation.Routes
import com.kodarisu.mygameroom.ui.theme.MyGameRoomTheme
import com.kodarisu.mygameroom.viewmodel.GamesViewModel
import com.kodarisu.mygameroom.viewmodel.GamesViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyGameRoomTheme {
                val gamesViewModel: GamesViewModel = viewModel(
                    factory = GamesViewModelFactory(application)
                )
                val navController = rememberNavController()
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route
                        ?: Routes.HOME


                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavBar(
                            currentRoute = currentRoute,
                            onNavigate = { route -> navController.navigate(route) },
                            modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
                        )
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        gamesViewModel = gamesViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}