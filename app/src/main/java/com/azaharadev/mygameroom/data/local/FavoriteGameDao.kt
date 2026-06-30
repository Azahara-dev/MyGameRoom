package com.azaharadev.mygameroom.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteGameDao {

    @Insert
    suspend fun insert(favorite: FavoriteGameEntity)

    @Delete
    suspend fun delete(favorite: FavoriteGameEntity)

    @Query("SELECT * FROM favorite_games")
    suspend fun getAllFavorites(): List<FavoriteGameEntity>

    @Query("SELECT * FROM favorite_games")
    fun getAllFavoritesFlow(): Flow<List<FavoriteGameEntity>>

}