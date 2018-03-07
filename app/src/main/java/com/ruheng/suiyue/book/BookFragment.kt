package com.ruheng.suiyue.book

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.AbsoluteLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import com.ruheng.suiyue.R
import com.ruheng.suiyue.base.BaseFragment
import com.ruheng.suiyue.data.bean.BookListBean
import com.ruheng.suiyue.data.bean.BooksItem
import com.ruheng.suiyue.widget.FloatTouchListener
import kotlinx.android.synthetic.main.float_btn.*
import kotlinx.android.synthetic.main.fragment_book.*


/**
 * Created by lvruheng on 2018/2/28.
 */
class BookFragment : BaseFragment(), BookContract.View {
    var mList = ArrayList<BooksItem>()
    private lateinit var mFloatTouchListener: FloatTouchListener
    private var mFloatViewBoundsInScreens: Rect? = null
    private var mEdgePadding: Int = 0
    private lateinit var mFloatRootView: AbsoluteLayout
    private lateinit var mFloatBtnWindowParams: AbsoluteLayout.LayoutParams
    lateinit var mPresenter: BookPresenter
    var mLastRefreshTime: Long = 0
    var mFloatBtnWrapper: LinearLayout? = null
    private var mAdapter: BookAdapter?=null

    override fun initView(savedInstanceState: Bundle?) {
        rv_book.layoutManager = LinearLayoutManager(context)
        mAdapter = BookAdapter(context!!, mList)
        rv_book.adapter = mAdapter
        addFloatBtn()
        setTouchListener()
    }

    override fun onResume() {
        super.onResume()
        //距离上次刷新超过6分钟，重新加载数据
        if (System.currentTimeMillis().minus(mLastRefreshTime) > 3600000) {
            mPresenter.start()
        }
        mLastRefreshTime = System.currentTimeMillis()
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_book
    }

    override fun setBookList(bookListBean: BookListBean) {
        if (mList?.size!! > 0) {
            mList?.clear()
        }
        bookListBean.books?.forEach {
            mList?.add(it)
        }

    }


    /**
     * 添加浮动按钮
     */
    fun addFloatBtn() {
        mFloatBtnWrapper = LayoutInflater.from(activity).inflate(R.layout.float_btn, null, false) as LinearLayout?
        mFloatBtnWindowParams = AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0)
        mFloatRootView = AbsoluteLayout(activity)
        val lp = RelativeLayout.LayoutParams(

                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        rl_root.addView(mFloatRootView)
        mFloatRootView.addView(mFloatBtnWrapper, mFloatBtnWindowParams)
        mFloatRootView.layoutParams = lp
    }


    /**
     * 设置触摸监听
     */
    fun setTouchListener() {
        val scale = resources.displayMetrics.density
        mEdgePadding = (10 * scale + 0.5).toInt()
        rl_root.post {
            mFloatViewBoundsInScreens = Rect()
            val mainLocation = IntArray(2)
            rl_root.getLocationOnScreen(mainLocation)
            mFloatViewBoundsInScreens?.set(
                    mainLocation[0],
                    mainLocation[1],
                    mainLocation[0] + rl_root.width,
                    rl_root.height + mainLocation[1])
            mFloatTouchListener = FloatTouchListener(activity, mFloatViewBoundsInScreens, mFloatBtnWrapper,
                    mFloatBtnWindowParams, mainLocation[1], mEdgePadding)
            mFloatRootView.setOnTouchListener(mFloatTouchListener)
            mFloatRootView.setOnClickListener {
                val rotateAnimation = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
                rotateAnimation.duration = 1000
                rotateAnimation.repeatCount = 3
                float_button.startAnimation(rotateAnimation)
                Toast.makeText(activity, "点击展现效果", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setPresenter(presenter: BookContract.Presenter) {
        mPresenter = presenter as BookPresenter
    }

    override fun getBookContext(): Context? {
        return if (isActive()) {
            context
        } else {
            null
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            //距离上次刷新超过6分钟，重新加载数据
            if (System.currentTimeMillis().minus(mLastRefreshTime) > 3600000) {
                mPresenter.start()
            }
            mLastRefreshTime = System.currentTimeMillis()
        }
    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}