package com.ruheng.suiyue.data.bean

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Cast(@SerializedName("alt")
                val alt: String = "",
                @SerializedName("name")
                val name: String = "",
                @SerializedName("id")
                val id: String = "",
                @SerializedName("avatars")
                val avatars: Avatars) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readParcelable<Avatars>(Avatars::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(alt)
        writeString(name)
        writeString(id)
        writeParcelable(avatars, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Cast> = object : Parcelable.Creator<Cast> {
            override fun createFromParcel(source: Parcel): Cast = Cast(source)
            override fun newArray(size: Int): Array<Cast?> = arrayOfNulls(size)
        }
    }
}