package com.marcospb.data.api

import com.marcospb.data.BuildConfig
import com.marcospb.data.models.MovieDetail
import com.marcospb.data.models.PopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {


    @GET("movie/now_playing")
    suspend fun getPlayingNow(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<PopularResponse>

    @GET("movie/popular")
    suspend fun getPopular(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<PopularResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<MovieDetail>


}