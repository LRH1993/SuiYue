package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class ShareInfo(@SerializedName("image")
                     val image: String = "",
                     @SerializedName("title")
                     val title: String = "",
                     @SerializedName("url")
                     val url: String = "",
                     @SerializedName("content")
                     val content: String = "")