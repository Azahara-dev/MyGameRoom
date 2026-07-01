package com.azaharadev.mygameroom.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.azaharadev.mygameroom.data.local.AppDatabase
import com.azaharadev.mygameroom.data.model.Game
import com.azaharadev.mygameroom.data.repository.FavoritesRepository
import com.azaharadev.mygameroom.data.repository.GamesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class GamesViewModel(application: Application) : AndroidViewModel(application) {

    private val gamesRepository = GamesRepository()
    private val favoritesRepository = FavoritesRepository(
        AppDatabase.getInstance(application).favoriteGameDao()
    )
    var searchResults by mutableStateOf<List<Game>?>(null)
        private set
    var games by mutableStateOf<List<Game>>(emptyList())
        private set

    var trendingGames by mutableStateOf<List<Game>>(emptyList())
        private set

    init {
        loadGames()
    }

    private suspend fun applyFavoriteStatus(gamesList: List<Game>): List<Game> {
        val favoriteIds = favoritesRepository.getFavoriteIds()
        return gamesList.map { game -> game.copy(isFavourite = favoriteIds.contains(game.id)) }
    }

    private fun loadGames() {
        viewModelScope.launch {
            val fetchedGames = gamesRepository.fetchGames()
            val fetchedTrending = gamesRepository.fetchTrendingGames()

            games = applyFavoriteStatus(fetchedGames)
            trendingGames = applyFavoriteStatus(fetchedTrending)
        }
    }
    fun toggleFavorite(gameId: Int) {
        viewModelScope.launch {
            val game = games.find { it.id == gameId }
                ?: searchResults?.find { it.id == gameId }
                ?: return@launch

            if (game.isFavourite) {
                favoritesRepository.removeFavorite(gameId)
            } else {
                favoritesRepository.addFavorite(gameId)
            }

            games = games.map {
                if (it.id == gameId) it.copy(isFavourite = !it.isFavourite) else it
            }

            searchResults = searchResults?.map {
                if (it.id == gameId) it.copy(isFavourite = !it.isFavourite) else it
            }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            searchResults = if (query.isBlank()) {
                null
            } else {
                applyFavoriteStatus(gamesRepository.searchGames(query))
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val favoriteGamesFlow: Flow<List<Game>> = favoritesRepository.getFavoriteIdsFlow()
        .flatMapLatest { ids ->
            flow {
                emit(gamesRepository.getGamesByIds(ids.toList()))
            }
        }
}