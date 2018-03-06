package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class Weibo(@SerializedName("imgUrl")
                 val imgUrl: String = "",
                 @SerializedName("link")
                 val link: String = "",
                 @SerializedName("audio")
                 val audio: String = "",
                 @SerializedName("title")
                 val title: String = "",
                 @SerializedName("desc")
                 val desc: String = "")