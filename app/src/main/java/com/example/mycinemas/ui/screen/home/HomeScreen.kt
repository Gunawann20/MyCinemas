package com.example.mycinemas.ui.screen.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycinemas.di.Injection
import com.example.mycinemas.model.Movie
import com.example.mycinemas.ui.ViewModelFactory
import com.example.mycinemas.ui.common.UiState
import com.example.mycinemas.ui.components.HomeUser
import com.example.mycinemas.ui.components.ItemMovie
import com.example.mycinemas.ui.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
    navigateToProfile: () -> Unit
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        val query by viewModel.query
        when(uiState){
            is UiState.Loading ->{
                viewModel.getAllMovies()
            }
            is UiState.Success -> {
                Column{
                    HomeUser(navigateToProfile = navigateToProfile)
                    SearchBar(query = query, onQueryChange = viewModel::searchMovies)
                    Text(
                        text = "Top Movies",
                        style = MaterialTheme.typography.subtitle2.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
                    )
                    HomeContent(
                        movies = uiState.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail
                    )
                }
            }
            is UiState.Error -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Tidak bisa memuat data")
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        items(movies) { data ->
            ItemMovie(image = data.image, title = data.title, modifier = Modifier.clickable { navigateToDetail(data.id) })
        }
    }
}