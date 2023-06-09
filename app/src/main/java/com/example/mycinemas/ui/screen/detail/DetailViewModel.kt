package com.example.mycinemas.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycinemas.data.MovieRepository
import com.example.mycinemas.model.Movie
import com.example.mycinemas.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MovieRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Movie>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Movie>>
        get() = _uiState

    fun getMovieById(movieId: Long){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getMovieById(movieId))
        }
    }
}