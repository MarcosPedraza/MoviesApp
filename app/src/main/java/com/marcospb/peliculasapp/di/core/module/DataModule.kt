package com.marcospb.peliculasapp.di.core.module

import com.marcospb.data.api.MoviesApiService
import com.marcospb.data.repository.MoviesRepositoryImp
import com.marcospb.data.utils.DispatchersProvider
import com.marcospb.domain.repository.MovieRepository
import com.marcospb.domain.usecase.GetMostPopularUseCase
import com.marcospb.domain.usecase.GetMovieDetailUseCase
import com.marcospb.domain.usecase.GetPlayNowMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Provides
    @Singleton
    fun providesMovieRepositiry(
        apiService: MoviesApiService,
        dispatchersProvider: DispatchersProvider
    ): MovieRepository {
        return MoviesRepositoryImp(apiService, dispatchersProvider)
    }


    //use cases injection -----
    @Provides
    fun providesGetPopularMoviesUseCase(repository: MovieRepository): GetMostPopularUseCase {
        return GetMostPopularUseCase(repository)
    }

    @Provides
    fun providesGetNowPlayinhUseCase(repository: MovieRepository): GetPlayNowMoviesUseCase {
        return GetPlayNowMoviesUseCase(repository)
    }


    @Provides
    fun provideGetMovieDetailUseCase(repository: MovieRepository): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(repository)
    }


}