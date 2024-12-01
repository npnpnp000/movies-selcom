package com.movies_selcom.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.movies_selcom.data.local.dao.LocalDao
import com.movies_selcom.model.entities.MovieEntity
import com.movies_selcom.utils.constants.DatabaseConstants.MOVIES_DATABASE

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieRoomDatabase : RoomDatabase(){

     abstract fun localDao() : LocalDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun getDatabase(context: Context): MovieRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    MOVIES_DATABASE
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}