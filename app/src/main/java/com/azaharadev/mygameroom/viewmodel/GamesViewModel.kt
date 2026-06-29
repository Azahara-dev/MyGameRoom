package com.azaharadev.mygameroom.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azaharadev.mygameroom.data.local.AppDatabase
import com.azaharadev.mygameroom.data.model.Game
import com.azaharadev.mygameroom.data.repository.FavoritesRepository
import com.azaharadev.mygameroom.data.repository.GamesRepository
import kotlinx.coroutines.launch

class GamesViewModel(application: Application) : AndroidViewModel(application) {

    private val gamesRepository = GamesRepository()
    private val favoritesRepository = FavoritesRepository(
        AppDatabase.getInstance(application).favoriteGameDao()
    )
    var games by mutableStateOf<List<Game>>(emptyList())
        private set

    init {
        loadGames()
    }

    private fun loadGames() {
        viewModelScope.launch {
            val fetchedGames = gamesRepository.fetchGames()
            val favoriteIds = favoritesRepository.getFavoriteIds()

            games = fetchedGames.map { game ->
                game.copy(isFavourite = favoriteIds.contains(game.id))
            }
        }
    }
    fun toggleFavorite(gameId: Int) {
        viewModelScope.launch {
            val game = games.find { it.id == gameId } ?: return@launch

            if (game.isFavourite) {
                favoritesRepository.removeFavorite(gameId)
            } else {
                favoritesRepository.addFavorite(gameId)
            }

            games = games.map {
                if (it.id == gameId) it.copy(isFavourite = !it.isFavourite) else it
            }
        }
    }
}