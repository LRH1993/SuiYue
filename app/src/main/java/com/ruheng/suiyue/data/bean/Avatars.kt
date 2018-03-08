package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class Avatars(@SerializedName("small")
                   val small: String = "",
                   @SerializedName("large")
                   val large: String = "",
                   @SerializedName("medium")
                   val medium: String = "")