package com.example.assignmentmovie.presentation.movielist.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.assignmentmovie.common.ViewState
import com.example.assignmentmovie.domain.model.Movie
import com.example.assignmentmovie.presentation.R
import com.example.assignmentmovie.presentation.movielist.viewmodel.MovieListViewModel

@Composable
fun MovieListScreen(
    movieListViewModel: MovieListViewModel,
    selectedMovie: (Int, String) -> Unit,
    modifier: Modifier
) {
    val resultValue = movieListViewModel.movieListStateFlow.collectAsState()
    val context = LocalContext.current

    when (resultValue.value) {
        is ViewState.Error -> Toast.makeText(
            context,
            (resultValue.value as ViewState.Error).message,
            Toast.LENGTH_SHORT
        ).show()

        is ViewState.Loading -> Toast.makeText(
            context,
            stringResource(id = R.string.fetching_movies),
            Toast.LENGTH_SHORT
        ).show()

        is ViewState.Success -> MoviesListView(
            (resultValue.value as ViewState.Success<List<Movie>>).data,
            selectedMovie, modifier
        )
    }
}






