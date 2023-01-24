package com.marcospb.domain.usecase



import com.marcospb.domain.models.Movie
import com.marcospb.domain.repository.MovieRepository


class GetPlayNowMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(): com.marcospb.domain.utils.Result<List<Movie>> = repository.getPlayNowMovies()
}