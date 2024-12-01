package com.movies_selcom.dependency_injection

import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//import com.appa.view.fragment.dashboard.viewmodel.MainViewModel
import com.movies_selcom.view.fragment.splash.viewmodel.SplashViewModel
import com.movies_selcom.utils.application.App
import com.movies_selcom.utils.factories.ViewModelFactory
import com.movies_selcom.view.fragment.dashboard.viewmodel.MainViewModel
import com.movies_selcom.view.fragment.details.viewmodel.DetailsViewModel

object ViewModelModule {

    inline fun <reified VM : ViewModel> provideViewModel(fragment: Fragment): Lazy<VM> {
        val viewModelFactory = when (VM::class.java) {
             SplashViewModel::class.java -> {
                val connectivityManager = App.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                ViewModelFactory(RepositoryModule.provideRepository(), connectivityManager)
            }
            MainViewModel::class.java -> {
                ViewModelFactory(RepositoryModule.provideRepository())
            }
            DetailsViewModel::class.java -> {
                ViewModelFactory(RepositoryModule.provideRepository())
            }
            else -> throw RuntimeException("ViewModel does not exist")
        }
        return lazy { ViewModelProvider(fragment, viewModelFactory)[VM::class.java] }
    }
}