package com.marcospb.domain.repository

import com.marcospb.domain.models.Movie
import com.marcospb.domain.models.MovieDetailDomain


interface MovieRepository {

    suspend fun getPopularMovies(): com.marcospb.domain.utils.Result<List<Movie>>

    suspend fun getPlayNowMovies(): com.marcospb.domain.utils.Result<List<Movie>>

    suspend fun getMOvieDetail(movieId: Int): com.marcospb.domain.utils.Result<MovieDetailDomain>

}