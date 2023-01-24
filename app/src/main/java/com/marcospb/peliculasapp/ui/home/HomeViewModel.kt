package com.marcospb.peliculasapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcospb.data.utils.DispatchersProvider
import com.marcospb.domain.models.Movie
import com.marcospb.domain.usecase.GetMostPopularUseCase
import com.marcospb.domain.usecase.GetPlayNowMoviesUseCase
import com.marcospb.domain.utils.onError
import com.marcospb.domain.utils.onSuccess
import com.marcospb.peliculasapp.mapper.MovieModelMapper
import com.marcospb.peliculasapp.models.MovieItem
import com.marcospb.peliculasapp.ui.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNowPlayNowMoviesUseCase: GetPlayNowMoviesUseCase,
    private val getMostPopularUseCase: GetMostPopularUseCase,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    private val uiState: MutableLiveData<UiState> = MutableLiveData()
    fun getUIState(): LiveData<UiState> = uiState


    private val uiStateMostPopular: MutableLiveData<UiStateMostPopular> = MutableLiveData()
    fun getUIStateMostPopular(): LiveData<UiStateMostPopular> = uiStateMostPopular


    fun getNowPlayingMovies() = launchOnIO {
        uiState.postValue(UiState.Loading)
        getNowPlayNowMoviesUseCase.invoke().onSuccess { result ->
            uiState.postValue(
                UiState.HomeSuccess(result.map {
                    MovieModelMapper.toPresentation(it)
                })
            )
        }.onError {
            uiState.postValue(UiState.NotLoading)
            uiState.postValue(UiState.Error("error:${it.message}"))
        }
    }


    fun getMostPopular() = launchOnIO {
        uiStateMostPopular.postValue(UiStateMostPopular.Loading)
        getMostPopularUseCase().onSuccess {
                result ->
            uiStateMostPopular.postValue(UiStateMostPopular.HomeSuccessMostPopular(
                result.map {
                    MovieModelMapper.toPresentation(it)
                }
            ))
        }.onError {
            uiStateMostPopular.postValue(UiStateMostPopular.Error("${it.message}"))
        }

    }


    sealed class UiState {
        data class HomeSuccess(val movieList: List<MovieItem>) : UiState()
        data class Error(val message: String?) : UiState()
        object Loading : UiState()
        object NotLoading : UiState()
    }


    sealed class UiStateMostPopular {
        data class HomeSuccessMostPopular(val movieList: List<MovieItem>) : UiStateMostPopular()
        data class Error(val message: String?) : UiStateMostPopular()
        object Loading : UiStateMostPopular()
        object NotLoading : UiStateMostPopular()
    }

}