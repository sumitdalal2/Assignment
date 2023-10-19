package com.example.assignmentmovie.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.assignmentmovie.common.Constants
import com.example.assignmentmovie.common.Constants.Companion.MOVIE_DETAIL_SCREEN_PATH
import com.example.assignmentmovie.common.Constants.Companion.MOVIE_LIST_SCREEN_PATH
import com.example.assignmentmovie.presentation.R
import com.example.assignmentmovie.presentation.moviedetails.ui.MovieDetailsScreen
import com.example.assignmentmovie.presentation.moviedetails.viewmodel.MovieDetailsViewModel
import com.example.assignmentmovie.presentation.movielist.ui.MovieListScreen
import com.example.assignmentmovie.presentation.movielist.viewmodel.MovieListViewModel

@Composable
fun AppNavHost(
    navHostController: NavHostController/* = rememberNavController()*/,
    movieListViewModel: MovieListViewModel,
    movieDetailsViewModel: MovieDetailsViewModel,
    modifier: Modifier,
    toolBarTitle: MutableState<String>,
    secondaryScreenHeader: MutableState<Boolean>,
) {
    NavHost(navController = navHostController, startDestination = MOVIE_LIST_SCREEN_PATH) {
        composable(route = MOVIE_LIST_SCREEN_PATH) {
            toolBarTitle.value = stringResource(id = R.string.app_name)
            secondaryScreenHeader.value = false
            MovieListScreen(
                movieListViewModel = movieListViewModel,
                selectedMovie = { movieID: Int, title: String ->
                    navHostController.navigate("$MOVIE_DETAIL_SCREEN_PATH/$movieID/$title")

                },
                modifier
            )
        }
        composable(
            route = "$MOVIE_DETAIL_SCREEN_PATH/{${Constants.MOVIE_ID}}/{${Constants.MOVIE_TITLE}}",
            arguments = listOf(
                navArgument(Constants.MOVIE_ID) {
                    type = NavType.IntType
                }, navArgument(Constants.MOVIE_TITLE) {
                    type = NavType.StringType
                }
            )
        ) {

            secondaryScreenHeader.value = true
            toolBarTitle.value =
                it.arguments?.getString(Constants.MOVIE_TITLE).toString()

            it.arguments?.getInt(Constants.MOVIE_ID)?.let { it1 ->
                MovieDetailsScreen(
                    movieDetailsViewModel = movieDetailsViewModel, selectedMovieId = it1, modifier
                )
            }
        }
    }

}