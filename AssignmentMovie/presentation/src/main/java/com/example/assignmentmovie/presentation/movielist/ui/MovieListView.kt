package com.example.assignmentmovie.presentation.movielist.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assignmentmovie.domain.model.Movie

@Composable
fun MoviesListView(
    movieList: List<Movie>,
    selectedMovie: (Int,String) -> Unit,
    modifier: Modifier
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(10.dp),
        content = {
            items(movieList.size) {
                MovieGridItem(movie = movieList[it], selectedMovie)
            }
        })
}