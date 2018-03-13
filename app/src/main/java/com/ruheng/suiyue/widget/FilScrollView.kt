package com.ruheng.suiyue.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView


/**
 * Created by lvruheng on 2018/3/13.
 */
class FilScrollView(context: Context, attrs: AttributeSet) : ScrollView(context, attrs) {
    private var mLastXIntercept: Float = 0f
    private var mLastYIntercept: Float = 0f
    //解决滑动冲突
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        var intercepted = false
        val x = ev?.x
        val y = ev?.y
        val action = ev?.action!! and MotionEvent.ACTION_MASK
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                intercepted = false
                //调用onInterceptTouchEvent方法初始化mActivePointerId
                super.onInterceptTouchEvent(ev)
            }
            MotionEvent.ACTION_MOVE -> {
                //横坐标位移增量
                val deltaX = x!! - mLastXIntercept
                //纵坐标位移增量
                val deltaY = y!! - mLastYIntercept
                intercepted = Math.abs(deltaX) < Math.abs(deltaY)
            }
            MotionEvent.ACTION_UP -> {
                intercepted = false
            }
        }
        mLastXIntercept = x!!
        mLastYIntercept = y!!
        return intercepted
    }
}