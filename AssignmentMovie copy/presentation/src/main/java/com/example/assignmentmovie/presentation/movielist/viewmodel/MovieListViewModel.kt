package com.example.assignmentmovie.presentation.movielist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentmovie.common.ViewState
import com.example.assignmentmovie.domain.model.Movie
import com.example.assignmentmovie.domain.usecase.MoviesListUseCase
import com.example.assignmentmovie.domain.usecase.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val moviesListUseCase: MoviesListUseCase) :
    ViewModel() {

    private val _movieListStateFlow =
        MutableStateFlow<ViewState<List<Movie>>>(ViewState.Loading)
    val movieListStateFlow: StateFlow<ViewState<List<Movie>>>
        get() = _movieListStateFlow

    init {
        getMovieList()
    }

    fun getMovieList() {
        viewModelScope.launch {
            moviesListUseCase().collect {
                when (it) {
                    is Response.Error -> _movieListStateFlow.value =
                        ViewState.Error(it.message)

                    is Response.Success -> _movieListStateFlow.value =
                        ViewState.Success(it.data.movies)
                }
            }
        }
    }
}