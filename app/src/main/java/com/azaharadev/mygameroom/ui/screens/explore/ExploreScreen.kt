package com.azaharadev.mygameroom.ui.screens.explore

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azaharadev.mygameroom.data.model.Genre
import com.azaharadev.mygameroom.ui.components.GameCard
import com.azaharadev.mygameroom.ui.components.GenreChip
import com.azaharadev.mygameroom.ui.components.SearchBar
import com.azaharadev.mygameroom.ui.theme.MyGameRoomTheme
import com.azaharadev.mygameroom.ui.theme.TextSecondary
import com.azaharadev.mygameroom.viewmodel.GamesViewModel
import kotlinx.coroutines.delay

@Composable
fun ExploreScreen(gamesViewModel: GamesViewModel) {
    var searchText by remember { mutableStateOf("") }
    var selectedGenre by remember { mutableStateOf<Genre?>(null) }

    val sourceList = gamesViewModel.searchResults ?: gamesViewModel.games

    val filteredGames = sourceList.filter { game ->
        val matchesGenre = selectedGenre == null || game.genres.contains(selectedGenre)
        matchesGenre
    }

    LaunchedEffect(searchText) {
        delay(200)
        gamesViewModel.search(searchText)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(
            value = searchText,
            onValueChange = { searchText = it }
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            GenreChip(
                "Todos",
                isSelected = (selectedGenre == null),
                onClick = { selectedGenre = null }
            )
            Genre.entries.forEach { genre ->
                GenreChip(
                    genre.name,
                    isSelected = (selectedGenre == genre),
                    onClick = { selectedGenre = genre }
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${filteredGames.size} juegos encontrados",
                color = TextSecondary,
                style = MaterialTheme.typography.labelSmall
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                /*Icon(
                    imageVector = Icons.Filled.FilterList,
                    contentDescription = null,
                    tint = TextSecondary
                )
                Text(
                    text = "Filtros",
                    color = TextSecondary,
                    style = MaterialTheme.typography.labelSmall
                )*/
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(filteredGames) { game ->
                GameCard(
                    game = game,
                    onFavoriteClick = { gamesViewModel.toggleFavorite(game.id) },
                    onCardClick = {}
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    MyGameRoomTheme {
        ExploreScreen(viewModel())
    }
}