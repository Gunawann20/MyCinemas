package com.example.mycinemas.model

data class Movie(
    val id: Long,
    val title: String,
    val rating: String,
    val year: Int,
    val image: String,
    val description: String,
    val genre: List<String>,
)
