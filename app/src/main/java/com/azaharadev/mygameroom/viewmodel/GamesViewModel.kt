package com.azaharadev.mygameroom.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.azaharadev.mygameroom.data.model.MockData

class GamesViewModel : ViewModel() {
    var games by mutableStateOf(MockData.games)
        private set

    fun toggleFavorite(gameId: Int) {
        games = games.map { game ->
            if (game.id == gameId) game.copy(isFavourite = !game.isFavourite)
            else game
        }
    }
}