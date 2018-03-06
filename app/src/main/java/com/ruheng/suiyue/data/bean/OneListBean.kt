package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class OneListBean(@SerializedName("res")
                       val res: Int = 0,
                       @SerializedName("data")
                       val data: Data)