package com.movies_selcom.view.fragment.details.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies_selcom.data.repositories.Repository
import com.movies_selcom.model.entities.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: Repository) : ViewModel() {

    var movieInFavoriteLiveData: MutableLiveData<MovieEntity?> = MutableLiveData<MovieEntity?>()

     fun getMovie(id :Int) = viewModelScope.launch {
        repository.getMovie(id).collect {
            movieInFavoriteLiveData.value = it
        }
    }
    fun removeMovieFromFavorite(){
        deleteMovie()


    }

    private fun deleteMovie() = viewModelScope.launch(Dispatchers.IO) {
        Log.e("deleteMovie", movieInFavoriteLiveData.value?.id.toString())
        movieInFavoriteLiveData.value?.let {
            Log.e("deleteMovie",it.id.toString())
            repository.deleteMovie(it.id)

            movieInFavoriteLiveData.postValue(null)

        }

    }

    fun saveMovieToFavorite(movieEntity: MovieEntity?){
        movieEntity?.let {
            movieInFavoriteLiveData.value = movieEntity

            saveMovieEntity(movieEntity)
        }
    }
    private fun saveMovieEntity(movieEntity: MovieEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveMovieEntity(movieEntity)
    }
}