package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class Rating(@SerializedName("average")
                  val average: String = "",
                  @SerializedName("min")
                  val min: Int = 0,
                  @SerializedName("max")
                  val max: Int = 0,
                  @SerializedName("numRaters")
                  val numRaters: Int = 0)