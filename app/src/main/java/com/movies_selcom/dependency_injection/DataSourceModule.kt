package com.movies_selcom.dependency_injection

//import com.appa.data.local.souce.MessagesLocalDataSource
import com.movies_selcom.data.local.souce.LocalDataSource
import com.movies_selcom.data.remote.source.RemoteDataSource

object DataSourceModule {

    fun provideLocalDataSource(): LocalDataSource {
        return LocalDataSource(DaoModule.provideDao())
    }

    fun provideRemoteDataSource() : RemoteDataSource {
        return RemoteDataSource(NetworkModule.provideMessagesApi())
    }

}