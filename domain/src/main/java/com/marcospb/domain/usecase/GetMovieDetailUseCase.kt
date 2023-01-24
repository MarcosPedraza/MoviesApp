package com.marcospb.domain.usecase

import com.marcospb.domain.models.MovieDetailDomain
import com.marcospb.domain.repository.MovieRepository

class GetMovieDetailUseCase(private val movieRepository: MovieRepository) {

    suspend fun invoke(idMovie: Int): com.marcospb.domain.utils.Result<MovieDetailDomain> {
        return movieRepository.getMOvieDetail(idMovie)
    }
}