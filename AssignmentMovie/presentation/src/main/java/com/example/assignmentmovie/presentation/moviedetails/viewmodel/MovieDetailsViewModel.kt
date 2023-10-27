package com.example.assignmentmovie.presentation.moviedetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentmovie.common.ViewState
import com.example.assignmentmovie.domain.model.MovieDetailInfo
import com.example.assignmentmovie.domain.usecase.MovieDetailsUseCase
import com.example.assignmentmovie.domain.usecase.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: MovieDetailsUseCase
) : ViewModel() {

    private val _movieDetailInfoStateFlow =
        MutableStateFlow<ViewState<MovieDetailInfo>>(ViewState.Loading)
    val movieDetailInfoStateFlow: StateFlow<ViewState<MovieDetailInfo>>
        get() = _movieDetailInfoStateFlow


    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieDetailsUseCase(movieId).collect() {
                when (it) {
                    is Response.Error -> _movieDetailInfoStateFlow.value =
                        ViewState.Error(it.message)

                    is Response.Success -> _movieDetailInfoStateFlow.value =
                        ViewState.Success(it.data)
                }
            }
        }
    }
}