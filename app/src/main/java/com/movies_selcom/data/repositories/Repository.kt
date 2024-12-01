package com.movies_selcom.data.repositories

//import com.appa.data.local.souce.MessagesLocalDataSource
//import com.appa.model.entities.MessageEntity
import com.movies_selcom.data.local.souce.LocalDataSource
import com.movies_selcom.data.remote.source.RemoteDataSource
import com.movies_selcom.model.entities.MovieEntity


class Repository(
    private val localDataSource : LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    @Throws()
    suspend fun getPopularMovies() = remoteDataSource.getPopularMovies()

    @Throws()
    suspend fun getBroadcastMovies() = remoteDataSource.getBroadcastMovies()

    suspend fun saveMovieEntity(movieEntity: MovieEntity) {
        localDataSource.saveMovieEntity(movieEntity)
    }

    fun getAllMovies() = localDataSource.getAllMovies()

    fun getMovie(id: Int) = localDataSource.getMovie(id)

    suspend fun deleteMovie(id: Int) {
        localDataSource.deleteMovie(id)
    }
}