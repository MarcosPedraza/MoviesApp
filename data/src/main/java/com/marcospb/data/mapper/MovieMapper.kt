package com.marcospb.data.mapper

import com.marcospb.data.models.MovieDetail
import com.marcospb.data.models.MovieItem
import com.marcospb.domain.models.Movie
import com.marcospb.domain.models.MovieDetailDomain

object MovieMapper {

    fun toDomain(item: MovieItem): Movie = Movie(
        id = item.id,
        title = item.title,
        posterImg = item.posterPath ?: ""
    )

    fun toDomain(movieDetail: MovieDetail): MovieDetailDomain = MovieDetailDomain(
        title = movieDetail.title ?: "",
        description = movieDetail.overview ?: "",
        score = movieDetail.voteAverage.toString(),
        posterPath = movieDetail.posterPath ?: "",
        voteAverage = movieDetail.voteCount
    )

}