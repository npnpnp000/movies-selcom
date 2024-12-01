package com.movies_selcom.view.fragment.dashboard.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.movies_selcom.data.repositories.Repository
import com.movies_selcom.model.entities.MovieEntity
import com.movies_selcom.model.response_models.Result
import com.movies_selcom.utils.extensions.MoviesType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(private val repository: Repository) : ViewModel() {

    var moviesLiveData: MutableLiveData<ArrayList<MovieEntity>?> =
        MutableLiveData<ArrayList<MovieEntity>?>()

    init {
        requestMovies(0)
    }

    @Throws()
    private fun getPopularMovies() = viewModelScope.launch {
        moviesLiveData.value = convertToMovie(repository.getPopularMovies().results)
    }

    @Throws()
    private fun getBroadcastMovies() = viewModelScope.launch {
        moviesLiveData.value = convertToMovie(repository.getBroadcastMovies().results)
    }

    private fun convertToMovie(list: ArrayList<Result>): ArrayList<MovieEntity> {

        val returnList = arrayListOf<MovieEntity>()

        list.map {
            returnList.add(
                MovieEntity(
                    it.title,
                    id = it.id,
                    image_path = it.poster_path,
                    overview = it.overview
                )
            )
        }
        return returnList
    }

    fun requestMovies(position: Int) {
        try {
            when (position) {
                0 -> {
                    getPopularMovies()
                }

                1 -> {
                    getBroadcastMovies()
                }

                2 -> {
                    getAllFavoritesMovies()
                }
            }
        }catch (e : Exception){
            Log.e("RemoteDataException", e.message.toString())
        }

    }

    private fun getAllFavoritesMovies() = viewModelScope.launch {
        repository.getAllMovies().collect {
            moviesLiveData.value = it?.toCollection(ArrayList())
        }
    }

}


