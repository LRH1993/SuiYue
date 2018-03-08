package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class MovieListBean(@SerializedName("total")
                         val total: Int = 0,
                         @SerializedName("subjects")
                         val subjects: List<SubjectsItem>?,
                         @SerializedName("count")
                         val count: Int = 0,
                         @SerializedName("start")
                         val start: Int = 0,
                         @SerializedName("title")
                         val title: String = "")