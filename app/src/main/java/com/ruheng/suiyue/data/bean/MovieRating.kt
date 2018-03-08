package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class MovieRating(@SerializedName("average")
                       val average: Double = 0.0,
                       @SerializedName("min")
                       val min: Int = 0,
                       @SerializedName("max")
                       val max: Int = 0,
                       @SerializedName("stars")
                       val stars: String = "")