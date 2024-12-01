package com.movies_selcom.view.fragment.splash.viewmodel

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*

class SplashViewModel(
    private val connectivityManager: ConnectivityManager?
) : ViewModel() {

    var internetConnectionAvailable: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        checkInternetConnectivity()
    }

    private fun checkInternetConnectivity() {
        if (connectivityManager == null) {
            internetConnectionAvailable.value = false
            return
        }
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) ?: return
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            internetConnectionAvailable.value = true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            internetConnectionAvailable.value = true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            internetConnectionAvailable.value = true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
            internetConnectionAvailable.value = true
        }

    }

}
