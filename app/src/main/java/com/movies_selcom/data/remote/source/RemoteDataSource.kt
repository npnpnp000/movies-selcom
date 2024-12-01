package com.movies_selcom.data.remote.source

import com.movies_selcom.data.remote.api.MoviesApi
import com.movies_selcom.model.response_models.MoviesModel

class RemoteDataSource(private val moviesApi: MoviesApi) {

    @Throws()
    suspend fun getPopularMovies(): MoviesModel{
        return moviesApi.getPopularMovies()


    }

    @Throws()
    suspend fun getBroadcastMovies(): MoviesModel{
        return moviesApi.getBroadcastMovies()


    }

}