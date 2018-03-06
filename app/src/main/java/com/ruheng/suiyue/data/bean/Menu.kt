package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class Menu(@SerializedName("vol")
                val vol: String = "",
                @SerializedName("list")
                val list: List<ListItem>?)