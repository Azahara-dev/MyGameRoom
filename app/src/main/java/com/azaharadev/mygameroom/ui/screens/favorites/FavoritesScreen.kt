package com.azaharadev.mygameroom.ui.screens.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azaharadev.mygameroom.ui.theme.TextPrimary
import com.azaharadev.mygameroom.viewmodel.GamesViewModel

@Composable
fun FavoritesScreen(gamesViewModel: GamesViewModel = viewModel()) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Pantalla Favoritos", color = TextPrimary)
    }
}