package com.example.mycinemas.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycinemas.data.MovieRepository
import com.example.mycinemas.model.Movie
import com.example.mycinemas.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Movie>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Movie>>>
        get() = _uiState

    fun getAllMovies(){
        viewModelScope.launch {
            repository.getAllMovies()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun searchMovies(query: String){
        _query.value = query
        viewModelScope.launch {
            repository.searchMovies(_query.value)
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}