package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class Data(@SerializedName("date")
                val date: String = "",
                @SerializedName("content_list")
                val contentList: List<ContentListItem>?,
                @SerializedName("weather")
                val weather: Weather,
                @SerializedName("id")
                val id: String = "")