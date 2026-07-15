package com.azaharadev.mygameroom.ui.screens.home

import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azaharadev.mygameroom.ui.components.GameCard
import com.azaharadev.mygameroom.ui.theme.AccentOrange
import com.azaharadev.mygameroom.ui.theme.AccentPrimary
import com.azaharadev.mygameroom.ui.theme.MyGameRoomTheme
import com.azaharadev.mygameroom.ui.theme.TextPrimary
import com.azaharadev.mygameroom.ui.theme.TextSecondary
import com.azaharadev.mygameroom.viewmodel.GamesViewModel

@Composable
fun HomeScreen(
    gamesViewModel: GamesViewModel
) {
    when {
        gamesViewModel.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = AccentPrimary)
            }
        }

        gamesViewModel.errorMessage != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.WifiOff,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = gamesViewModel.errorMessage!!,
                        color = TextSecondary,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Button(onClick = { gamesViewModel.retry()  }) {
                        Text("Reintentar")
                    }
                }
            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "¿Que juego me empiezo hoy?",
                        color = TextPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        gamesViewModel.games.take(3).forEach { game ->
                            TodayGameCard(game = game, onClick = { })
                        }
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(
                                imageVector = Icons.Filled.Whatshot,
                                contentDescription = null,
                                tint = AccentOrange
                            )

                            Text(
                                text = "Tendencias",
                                color = TextPrimary,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }

                        /*Text(
                            text = "Ver todo",
                            color = AccentPrimary,
                            style = MaterialTheme.typography.bodyMedium
                        )*/
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        gamesViewModel.trendingGames.take(20).forEach { game ->
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
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyGameRoomTheme {
        HomeScreen(viewModel())
    }
}