package com.azaharadev.mygameroom.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_games")
data class FavoriteGameEntity(
    @PrimaryKey val gameId: Int
)
