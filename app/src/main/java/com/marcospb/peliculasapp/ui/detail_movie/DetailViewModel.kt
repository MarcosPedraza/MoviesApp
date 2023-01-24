package com.marcospb.peliculasapp.ui.detail_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcospb.data.utils.DispatchersProvider
import com.marcospb.domain.usecase.GetMovieDetailUseCase
import com.marcospb.domain.utils.onError
import com.marcospb.domain.utils.onSuccess
import com.marcospb.peliculasapp.mapper.MovieModelMapper
import com.marcospb.peliculasapp.models.MovieDetailItem
import com.marcospb.peliculasapp.ui.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val dispatcher: DispatchersProvider,
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : BaseViewModel(dispatcher) {

    private val uiStateDetail = MutableLiveData<UiDetailState>()
    fun getDetailState(): LiveData<UiDetailState> = uiStateDetail


    fun getDetailMovieById(idMovie: Int) = launchOnIO {
        uiStateDetail.postValue(UiDetailState.Loading)
        getMovieDetailUseCase.invoke(idMovie).onSuccess {
            uiStateDetail.postValue(UiDetailState.OnSuccess(MovieModelMapper.toPresentationDetail(it)))
        }.onError {
            uiStateDetail.postValue(UiDetailState.OnError("${it.message}"))
        }
    }


    sealed class UiDetailState {
        data class OnSuccess(val detailMovie: MovieDetailItem) : UiDetailState()
        data class OnError(val message: String) : UiDetailState()
        object Loading : UiDetailState()
    }


}