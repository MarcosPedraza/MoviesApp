package com.marcospb.peliculasapp.models

import android.os.Parcel
import android.os.Parcelable
import com.marcospb.peliculasapp.ui.utils.IMAGE_URL

data class MovieDetailItem(
    val title: String,
    val score: String,
    val description: String,
    val voteAverage: Int,
    val posterPath: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(score)
        parcel.writeString(description)
        parcel.writeInt(voteAverage)
        parcel.writeString(posterPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDetailItem> {
        override fun createFromParcel(parcel: Parcel): MovieDetailItem {
            return MovieDetailItem(parcel)
        }

        override fun newArray(size: Int): Array<MovieDetailItem?> {
            return arrayOfNulls(size)
        }
    }

    fun getPosterImage() = IMAGE_URL + this.posterPath

}