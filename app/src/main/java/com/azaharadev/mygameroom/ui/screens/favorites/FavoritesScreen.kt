package com.azaharadev.mygameroom.ui.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azaharadev.mygameroom.ui.components.GameCard
import com.azaharadev.mygameroom.ui.theme.MyGameRoomTheme
import com.azaharadev.mygameroom.ui.theme.TextSecondary
import com.azaharadev.mygameroom.viewmodel.GamesViewModel

@Composable
fun FavoritesScreen(gamesViewModel: GamesViewModel = viewModel()) {

    val favoriteGames = gamesViewModel.games.filter { it.isFavourite }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (favoriteGames.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Filled.HeartBroken,
                    tint = TextSecondary,
                    contentDescription = null
                )
                Text("Aún no tienes ningún favorito", color = TextSecondary)

            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                items(favoriteGames) { game ->
                    GameCard(
                        game = game,
                        onFavoriteClick = { gamesViewModel.toggleFavorite(game.id) },
                        onCardClick = {}
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    MyGameRoomTheme {
        FavoritesScreen(viewModel())
    }
}