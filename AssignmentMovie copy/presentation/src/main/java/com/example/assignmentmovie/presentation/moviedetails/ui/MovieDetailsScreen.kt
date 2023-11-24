package com.example.assignmentmovie.presentation.moviedetails.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.assignmentmovie.common.ViewState
import com.example.assignmentmovie.domain.model.MovieDetailInfo
import com.example.assignmentmovie.presentation.R
import com.example.assignmentmovie.presentation.moviedetails.viewmodel.MovieDetailsViewModel


@Composable
fun MovieDetailsScreen(
    movieDetailsViewModel: MovieDetailsViewModel,
    selectedMovieId: Int,
    modifier: Modifier
) {
    LaunchedEffect(key1 = true) {
        movieDetailsViewModel.getMovieDetails(selectedMovieId)
    }
    val context = LocalContext.current
    val result = movieDetailsViewModel.movieDetailInfoStateFlow.collectAsState()

    when (result.value) {
        is ViewState.Error -> Toast.makeText(
            context,
            (result.value as ViewState.Error).message,
            Toast.LENGTH_SHORT
        ).show()

        is ViewState.Loading -> Toast.makeText(
            context,
            stringResource(id = R.string.fetching_details),
            Toast.LENGTH_SHORT
        ).show()

        is ViewState.Success -> MovieCard(
            (result.value as ViewState.Success<MovieDetailInfo>).data,
            modifier
        )
    }
}

