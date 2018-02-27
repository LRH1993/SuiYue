package com.ruheng.suiyue.app

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by lvruheng on 2018/2/27.
 */
class SuiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}