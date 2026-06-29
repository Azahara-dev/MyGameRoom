package com.azaharadev.mygameroom.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteGameEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteGameDao(): FavoriteGameDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mygameroom_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

