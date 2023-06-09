package com.example.mycinemas.ui.screen.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mycinemas.di.Injection
import com.example.mycinemas.model.Movie
import com.example.mycinemas.ui.ViewModelFactory
import com.example.mycinemas.ui.common.UiState
import com.example.mycinemas.ui.theme.Gold
import com.example.mycinemas.ui.theme.SoftGray

@Composable
fun DetailScreen(
    movieId: Long,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getMovieById(movieId)
            }
            is UiState.Success -> {
                DetailMovie(movie = uiState.data, onBackClick = navigateBack)
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
fun DetailMovie(
    movie: Movie,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = modifier){

            val imagePainter = rememberAsyncImagePainter(movie.image)
            Image(
                painter = imagePainter,
                contentDescription = "Image detail movie",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = modifier.size(40.dp).padding(top = 16.dp, start = 16.dp).clickable {
                    onBackClick()
                }
            )
        }
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = "Rating : ${movie.rating}",
                color = Color.White,
                modifier = modifier
                    .background(Gold, RoundedCornerShape(4.dp))
                    .padding(16.dp, 8.dp)
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Row(
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            ) {
                for (genre in movie.genre){
                    Text(
                        text = genre,
                        modifier = Modifier
                            .background(SoftGray, RoundedCornerShape(2.dp))
                            .padding(8.dp, 6.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }
            }
            Text(text = movie.description)
        }
    }
}