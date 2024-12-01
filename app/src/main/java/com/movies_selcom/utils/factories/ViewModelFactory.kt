package com.movies_selcom.utils.factories

import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movies_selcom.data.repositories.Repository
import com.movies_selcom.view.fragment.dashboard.viewmodel.MainViewModel
import com.movies_selcom.view.fragment.details.viewmodel.DetailsViewModel
//import com.appa.view.fragment.dashboard.viewmodel.MainViewModel
import com.movies_selcom.view.fragment.splash.viewmodel.SplashViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: Repository,
    private val connectivityManager: ConnectivityManager? = null
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when (modelClass) {
            MainViewModel::class.java -> MainViewModel(repository) as T
            DetailsViewModel::class.java -> DetailsViewModel(repository) as T
            SplashViewModel::class.java -> SplashViewModel(
                connectivityManager
            ) as T
            else -> throw Exception("ViewModel not found")
        }
}