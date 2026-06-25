package com.azaharadev.mygameroom.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azaharadev.mygameroom.data.model.Game
import com.azaharadev.mygameroom.data.repository.GamesRepository
import kotlinx.coroutines.launch

class GamesViewModel : ViewModel() {

    private val repository = GamesRepository()
    var games by mutableStateOf<List<Game>>(emptyList())
        private set

    init {
        loadGames()
    }

    private fun loadGames() {
        viewModelScope.launch {
            games = repository.fetchGames()
        }
    }
    fun toggleFavorite(gameId: Int) {
        games = games.map { game ->
            if (game.id == gameId) game.copy(isFavourite = !game.isFavourite)
            else game
        }
    }
}