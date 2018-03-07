package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class Weather(@SerializedName("date")
                   val date: String = "",
                   @SerializedName("city_name")
                   val cityName: String = "",
                   @SerializedName("temperature")
                   val temperature: String = "",
                   @SerializedName("humidity")
                   val humidity: String = "",
                   @SerializedName("wind_direction")
                   val windDirection: String = "",
                   @SerializedName("hurricane")
                   val hurricane: String = "",
                   @SerializedName("climate")
                   val climate: String = "")