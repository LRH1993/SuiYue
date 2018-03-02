package com.ruheng.suiyue.network

import com.google.gson.Gson
import okhttp3.Response
import java.io.IOException
import java.lang.IllegalArgumentException


/**
 * Created by lvruheng on 2018/3/2.
 */
class GsonParser<T>(clazz: Class<T>) : Parser<T> {
    var mClass: Class<T>

    init {
        if (clazz == null) {
            throw IllegalArgumentException("Class can't be null")
        } else {
            mClass = clazz
        }
    }

    override fun parse(response: Response): T? {
        try {
            var gson = Gson()
            val str = response.body().toString()
            val t = gson.fromJson(str, mClass)
            return t
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}