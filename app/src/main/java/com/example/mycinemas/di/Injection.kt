package com.example.mycinemas.di

import com.example.mycinemas.data.MovieRepository

object Injection {
    fun provideRepository(): MovieRepository{
        return MovieRepository.getInstance()
    }
}