package com.movies_selcom.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.movies_selcom.model.entities.MovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface LocalDao {

    @Insert
    suspend fun saveMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie_table ORDER BY id")
    fun getAllMoviesList() : Flow<List<MovieEntity>?>

    @Query("SELECT * FROM movie_table WHERE id=:id ")
    fun getMovie (id: Int): Flow<MovieEntity?>

    @Query("DELETE FROM movie_table WHERE id = :id")
    suspend fun deleteMovie(id: Int)


}