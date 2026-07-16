package com.kodarisu.mygameroom.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kodarisu.mygameroom.data.local.AppDatabase
import com.kodarisu.mygameroom.data.model.Game
import com.kodarisu.mygameroom.data.model.Genre
import com.kodarisu.mygameroom.data.repository.FavoritesRepository
import com.kodarisu.mygameroom.data.repository.GamesRepository
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

    var isLoading by mutableStateOf(true)
        private set

    var isFilterLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        loadGames()
    }

    fun retry() {
        loadGames()
    }

    private suspend fun applyFavoriteStatus(gamesList: List<Game>): List<Game> {
        val favoriteIds = favoritesRepository.getFavoriteIds()
        return gamesList.map { game -> game.copy(isFavourite = favoriteIds.contains(game.id)) }
    }

    private fun loadGames() {
        viewModelScope.launch {
            isLoading= true
            errorMessage = null
            try {
                val fetchedGames = gamesRepository.fetchGames()
                val fetchedTrending = gamesRepository.fetchTrendingGames()

                games = applyFavoriteStatus(fetchedGames)
                trendingGames = applyFavoriteStatus(fetchedTrending)
            } catch (e: Exception){
                errorMessage = "No se pudo cargar el contenido. Comprueba tu conexión e inténtalo de nuevo."
            } finally {
                isLoading = false
            }
        }
    }

    fun toggleFavorite(gameId: Int) {
        viewModelScope.launch {
            val game = games.find { it.id == gameId }
                ?: searchResults?.find { it.id == gameId }
                ?: trendingGames.find { it.id == gameId }
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

            trendingGames = trendingGames.map {
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
                val games = gamesRepository.getGamesByIds(ids.toList())
                emit(applyFavoriteStatus(games))
            }
        }

    fun filterByGenre(genre: Genre?) {
        viewModelScope.launch {
            isFilterLoading = true
            games = if (genre == null) {
                applyFavoriteStatus(gamesRepository.fetchGames())
            } else {
                applyFavoriteStatus(gamesRepository.getGamesByGenre(genre))
            }
            isFilterLoading = false
        }
    }
}