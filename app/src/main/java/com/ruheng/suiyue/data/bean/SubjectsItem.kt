package com.ruheng.suiyue.data.bean

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SubjectsItem(@SerializedName("images")
                        val images: Images,
                        @SerializedName("original_title")
                        val originalTitle: String = "",
                        @SerializedName("subtype")
                        val subtype: String = "",
                        @SerializedName("year")
                        val year: String = "",
                        @SerializedName("genres")
                        val genres: List<String>?,
                        @SerializedName("rating")
                        val rating: MovieRating,
                        @SerializedName("alt")
                        val alt: String = "",
                        @SerializedName("id")
                        val id: String = "",
                        @SerializedName("casts")
                        val casts: List<Cast>?,
                        @SerializedName("title")
                        val title: String = "",
                        @SerializedName("collect_count")
                        val collectCount: Int = 0) : Parcelable {
    constructor(source: Parcel) : this(
            source.readParcelable<Images>(Images::class.java.classLoader),
            source.readString(),
            source.readString(),
            source.readString(),
            source.createStringArrayList(),
            source.readParcelable<MovieRating>(MovieRating::class.java.classLoader),
            source.readString(),
            source.readString(),
            source.createTypedArrayList(Cast.CREATOR),
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(images, 0)
        writeString(originalTitle)
        writeString(subtype)
        writeString(year)
        writeStringList(genres)
        writeParcelable(rating, 0)
        writeString(alt)
        writeString(id)
        writeTypedList(casts)
        writeString(title)
        writeInt(collectCount)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SubjectsItem> = object : Parcelable.Creator<SubjectsItem> {
            override fun createFromParcel(source: Parcel): SubjectsItem = SubjectsItem(source)
            override fun newArray(size: Int): Array<SubjectsItem?> = arrayOfNulls(size)
        }
    }
}