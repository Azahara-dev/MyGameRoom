package com.azaharadev.mygameroom.data.repository

import com.azaharadev.mygameroom.data.local.FavoriteGameDao
import com.azaharadev.mygameroom.data.local.FavoriteGameEntity

class FavoritesRepository(private val dao: FavoriteGameDao) {

    suspend fun getFavoriteIds(): Set<Int> {
        return dao.getAllFavorites().map { it.gameId }.toSet()
    }

    suspend fun addFavorite(gameId: Int){
        dao.insert(FavoriteGameEntity(gameId))
    }

    suspend fun removeFavorite(gameId: Int) {
        dao.delete(FavoriteGameEntity(gameId))
    }
}