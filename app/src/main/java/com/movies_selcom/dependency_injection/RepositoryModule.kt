package com.movies_selcom.dependency_injection

import com.movies_selcom.data.repositories.Repository


object RepositoryModule {

    fun provideRepository(): Repository {
        return Repository(
            DataSourceModule.provideLocalDataSource(),
            DataSourceModule.provideRemoteDataSource()
        )
    }
}