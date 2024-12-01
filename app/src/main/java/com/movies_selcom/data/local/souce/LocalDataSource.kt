package com.movies_selcom.data.local.souce

import com.movies_selcom.data.local.dao.LocalDao
import com.movies_selcom.model.entities.MovieEntity

class LocalDataSource(private val localDao : LocalDao) {

    suspend fun saveMovieEntity(movieEntity: MovieEntity) {
        localDao.saveMovie(movieEntity)
    }

    fun getAllMovies() = localDao.getAllMoviesList()

    fun getMovie(id: Int) = localDao.getMovie(id)

    suspend fun deleteMovie(id: Int) {
        localDao.deleteMovie(id)
    }
}