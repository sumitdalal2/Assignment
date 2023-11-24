package com.example.assignmentmovie.di

import com.example.assignmentmovie.data.repository.IRepositoryImpl
import com.example.assignmentmovie.domain.repository.IRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieRepositoryModule {
    @Binds
    abstract fun bindMovieRepository(movieRepositoryImpl: IRepositoryImpl): IRepository
}