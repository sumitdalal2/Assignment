package com.example.assignmentmovie.di

import com.example.assignmentmovie.data.repository.datasource.MovieRemoteDataSource
import com.example.assignmentmovie.data.repository.datasource.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindMovieRemoteDataSource(remoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource
}