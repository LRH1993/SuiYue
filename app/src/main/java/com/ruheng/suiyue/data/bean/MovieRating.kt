package com.ruheng.suiyue.data.bean

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieRating(@SerializedName("average")
                       val average: Double = 0.0,
                       @SerializedName("min")
                       val min: Int = 0,
                       @SerializedName("max")
                       val max: Int = 0,
                       @SerializedName("stars")
                       val stars: String = "") : Parcelable {
    constructor(source: Parcel) : this(
            source.readDouble(),
            source.readInt(),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeDouble(average)
        writeInt(min)
        writeInt(max)
        writeString(stars)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieRating> = object : Parcelable.Creator<MovieRating> {
            override fun createFromParcel(source: Parcel): MovieRating = MovieRating(source)
            override fun newArray(size: Int): Array<MovieRating?> = arrayOfNulls(size)
        }
    }
}