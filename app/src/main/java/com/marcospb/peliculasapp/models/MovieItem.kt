package com.marcospb.peliculasapp.models

import com.marcospb.peliculasapp.ui.utils.IMAGE_URL

data class MovieItem(
    val id: Int,
    val title: String,
    val posterImg: String
) {
    fun getPosterUrl(): String = IMAGE_URL + this.posterImg

}
