package com.example.assignmentmovie.common

sealed class ViewState<out R> {
    data class Success<T>(val data: T) : ViewState<T>()
    data class Error(val message: String) : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
}