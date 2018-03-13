package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class DirectorsItem(@SerializedName("alt")
                         val alt: String = "",
                         @SerializedName("name")
                         val name: String = "",
                         @SerializedName("id")
                         val id: String = "",
                         @SerializedName("avatars")
                         val avatars: Avatars)