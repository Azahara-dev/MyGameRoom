package com.kodarisu.mygameroom.data.repository

import com.kodarisu.mygameroom.data.local.FavoriteGameDao
import com.kodarisu.mygameroom.data.local.FavoriteGameEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepository(private val dao: FavoriteGameDao) {

    suspend fun getFavoriteIds(): Set<Int> {
        return dao.getAllFavorites().map { it.gameId }.toSet()
    }

    fun getFavoriteIdsFlow(): Flow<Set<Int>> {
        return dao.getAllFavoritesFlow().map { entities ->
            entities.map { it.gameId }.toSet()
        }
    }

    suspend fun addFavorite(gameId: Int){
        dao.insert(FavoriteGameEntity(gameId))
    }

    suspend fun removeFavorite(gameId: Int) {
        dao.delete(FavoriteGameEntity(gameId))
    }
}