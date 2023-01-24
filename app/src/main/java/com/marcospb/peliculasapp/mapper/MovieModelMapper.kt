package com.marcospb.peliculasapp.mapper

import com.marcospb.domain.models.Movie
import com.marcospb.domain.models.MovieDetailDomain
import com.marcospb.peliculasapp.models.MovieDetailItem
import com.marcospb.peliculasapp.models.MovieItem

object MovieModelMapper {

    fun toPresentation(movieDomain: Movie): MovieItem {
        return MovieItem(
            movieDomain.id,
            movieDomain.title,
            movieDomain.posterImg
        )
    }

    fun toPresentationDetail(movieDetailDomain: MovieDetailDomain):MovieDetailItem = MovieDetailItem(
        title = movieDetailDomain.title,
        score = movieDetailDomain.score,
        description = movieDetailDomain.description,
        voteAverage = movieDetailDomain.voteAverage,
        posterPath = movieDetailDomain.posterPath
    )

}