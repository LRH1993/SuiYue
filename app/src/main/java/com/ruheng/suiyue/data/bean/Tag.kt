package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class Tag(@SerializedName("id")
               val id: String = "",
               @SerializedName("title")
               val title: String = "")