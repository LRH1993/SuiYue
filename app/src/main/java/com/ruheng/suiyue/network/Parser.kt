package com.ruheng.suiyue.network

import okhttp3.Response

/**
 * Created by lvruheng on 2018/3/2.
 */
interface Parser<T> {
    fun parse(response:Response): T?
}