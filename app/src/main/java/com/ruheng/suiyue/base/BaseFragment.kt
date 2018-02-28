package com.ruheng.suiyue.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by lvruheng on 2018/2/28.
 */
abstract class BaseFragment : Fragment() {
    var TAG: String? = null
    var mContentView: View? = null
    var mActivity: Activity? = null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        TAG = this.javaClass.simpleName
        mActivity = activity
    }

    override fun onDetach() {
        super.onDetach()
        mActivity = null
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            onUserVisible()
        } else {
            onUserInVisible()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mContentView == null) {
            mContentView = inflater?.inflate(getLayoutResources(), container, false)
        }
        return mContentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
    }
    abstract fun getLayoutResources(): Int
    /**
     * 初始化View控件
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 当fragment对用户可见时，会调用该方法，可在该方法中懒加载网络数据
     */
     fun onUserVisible() {

    }

    /**
     * 当fragment对用户不可见时，会调用该方法
     */
     fun onUserInVisible() {

    }
}