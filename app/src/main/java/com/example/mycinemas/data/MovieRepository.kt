package com.example.mycinemas.data

import com.example.mycinemas.model.FakeLikeMovieDataSource
import com.example.mycinemas.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MovieRepository {

    private val movies = mutableListOf<Movie>()

    init {
        if (movies.isEmpty()){
            FakeLikeMovieDataSource.dummyMovie.forEach{
                movies.add(it)
            }
        }
    }

    fun getAllMovies(): Flow<List<Movie>>{
        return flowOf(movies)
    }

    fun getMovieById(movieId: Long): Movie {
        return movies.first{
            it.id == movieId
        }
    }
    fun searchMovies(query: String): Flow<List<Movie>>{
        return flowOf(movies.filter {
            it.title.contains(query, ignoreCase = true)
        })
    }
    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(): MovieRepository =
            instance ?: synchronized(this) {
                MovieRepository().apply {
                    instance = this
                }
            }
    }
}