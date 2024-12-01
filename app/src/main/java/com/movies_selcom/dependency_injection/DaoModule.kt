package com.movies_selcom.dependency_injection

import com.movies_selcom.data.database.MovieRoomDatabase
import com.movies_selcom.data.local.dao.LocalDao
import com.movies_selcom.utils.application.App

object DaoModule {

    fun provideDao() : LocalDao{
        return MovieRoomDatabase.getDatabase(App.getApplicationContext()).localDao()
    }
}