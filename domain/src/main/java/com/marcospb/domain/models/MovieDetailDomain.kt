package com.marcospb.domain.models

data class MovieDetailDomain(
    val title: String,
    val score: String,
    val description: String,
    val voteAverage: Int,
    val posterPath: String
)
