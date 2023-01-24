package com.marcospb.data.repository

import com.marcospb.data.api.MoviesApiService
import com.marcospb.data.mapper.MovieMapper
import com.marcospb.data.models.PopularResponse
import com.marcospb.data.utils.DispatchersProvider
import com.marcospb.domain.models.Movie
import com.marcospb.domain.models.MovieDetailDomain
import com.marcospb.domain.repository.MovieRepository
import com.marcospb.domain.utils.Result

import kotlinx.coroutines.withContext


class MoviesRepositoryImp constructor(
    private val apiService: MoviesApiService,
    private val dispatchers: DispatchersProvider
) : MovieRepository {
    override suspend fun getPopularMovies(): com.marcospb.domain.utils.Result<List<Movie>> =
        withContext(dispatchers.getIO()) {
            return@withContext try {
                val result = apiService.getPopular()
                val list = result.body()?.results ?: listOf()


                com.marcospb.domain.utils.Result.Success(
                    list.map {
                        MovieMapper.toDomain(it)
                    }
                )

            } catch (exc: Exception) {
                com.marcospb.domain.utils.Result.Error(exc)
            }
        }

    override suspend fun getPlayNowMovies(): com.marcospb.domain.utils.Result<List<Movie>> =
        withContext(dispatchers.getIO()) {
            return@withContext try {
                val result = apiService.getPlayingNow()
                val list = result.body()?.results ?: listOf()
                com.marcospb.domain.utils.Result.Success(
                    list.map {
                        MovieMapper.toDomain(it)
                    }
                )

            } catch (exp: Exception) {
                com.marcospb.domain.utils.Result.Error(exp)
            }
        }

    override suspend fun getMOvieDetail(movieId: Int): Result<MovieDetailDomain> =
        withContext(dispatchers.getIO()) {
            return@withContext try {
                val result = apiService.getMovieDetail(movieId = movieId.toString())
                com.marcospb.domain.utils.Result.Success(
                    MovieMapper.toDomain(result.body()!!)
                )

            } catch (exp: Exception) {
                com.marcospb.domain.utils.Result.Error(exp)
            }
        }


}