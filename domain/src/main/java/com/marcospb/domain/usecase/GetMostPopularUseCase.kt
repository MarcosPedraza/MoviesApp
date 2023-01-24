package com.marcospb.domain.usecase


import com.marcospb.domain.models.Movie
import com.marcospb.domain.repository.MovieRepository


class GetMostPopularUseCase(
    private val moviesRepository: MovieRepository
) {
    suspend operator fun invoke(): com.marcospb.domain.utils.Result<List<Movie>> =
        moviesRepository.getPopularMovies()
}