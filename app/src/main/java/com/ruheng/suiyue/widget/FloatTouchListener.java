package com.ruheng.suiyue.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsoluteLayout;

import java.lang.ref.WeakReference;

/**
 * Created by lvruheng on 2018/3/7.
 */

public class FloatTouchListener implements View.OnTouchListener {
    private Rect mBoundsInScreen;
    private View mFloatView;
    private AbsoluteLayout.LayoutParams mFloatViewWindowParam;
    private int mParentMarginTop;
    private float mPreviousX;
    private float mPreviousY;
    private boolean mHasMoved;
    private int mTouchSlop;
    private int mEdgePaddingLeft;
    private int mEdgePaddingRight;
    private int mEdgePaddingTop;
    private int mEdgePaddingBottom;
    private FloatButtonCallback mFloatButtonCallback;
    private int mDownPointerId;
    private Interpolator mInterpolator;
    private FloatAnimatorUpdateListener mUpdateListener;

    public FloatTouchListener(Context context, Rect boundsInScreen, View floatView,
                              AbsoluteLayout.LayoutParams floatViewWindowParam, int parentMarginTop, int edgePadding) {
        mBoundsInScreen = boundsInScreen;
        mFloatView = floatView;
        mFloatViewWindowParam = floatViewWindowParam;
        mParentMarginTop = parentMarginTop;
        mInterpolator = new DecelerateInterpolator();
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        mEdgePaddingBottom = edgePadding;
        mEdgePaddingLeft = edgePadding;
        mEdgePaddingRight = edgePadding;
        mEdgePaddingTop = edgePadding;
    }

    /**
     * 调整floatview布局
     *
     * @param v
     * @param event
     * @return
     */
    private boolean adjustMarginParams(View v, MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();
        float deltaX = x - mPreviousX;
        float deltaY = y - mPreviousY;
        if (!mHasMoved) {
            if (Math.abs(deltaX) < mTouchSlop && Math.abs(deltaY) < mTouchSlop) {
                return false;
            }
        }
        //左上角位置
        int newX = (int) x - mFloatView.getWidth() / 2;
        int newY = (int) y - mFloatView.getHeight() / 2;
        newX = Math.max(newX, mBoundsInScreen.left + mEdgePaddingLeft);
        newX = Math.min(newX, mBoundsInScreen.right - mEdgePaddingRight - mFloatView.getWidth());
        newY = Math.max(newY, mBoundsInScreen.top + mEdgePaddingTop);
        newY = Math.min(newY, mBoundsInScreen.bottom - mEdgePaddingBottom - mFloatView.getHeight());
        mFloatViewWindowParam.x = newX;
        mFloatViewWindowParam.y = newY - mParentMarginTop;
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        if (mFloatButtonCallback != null) {
            mFloatButtonCallback.onTouch();
        }
        boolean result = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mDownPointerId = MotionEventCompat.getPointerId(event, 0);
                mPreviousX = event.getRawX();
                mPreviousY = event.getRawY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (mDownPointerId >= 0) {
                    int index = MotionEventCompat.getActionIndex(event);
                    int id = MotionEventCompat.getPointerId(event, index);
                    if (id == mDownPointerId) {
                        boolean update = adjustMarginParams(view, event);
                        if (!update) {
                            break;
                        }
                        mFloatView.requestLayout();
                        mHasMoved = true;
                        result = true;
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                if (mDownPointerId >= 0 && mHasMoved) {
                    event.setAction(MotionEvent.ACTION_CANCEL);
                    adjustMarginParams(view, event);
                    mFloatView.requestLayout();
                    int center = (mBoundsInScreen.width() - mFloatView.getWidth()) / 2;
                    int x = mFloatViewWindowParam.x;
                    int destX = 0;
                    int posX = Gravity.LEFT;
                    //抬起时 根据位置强制把浮动按钮归于左边或右边
                    if (x < center) { // 左边
                        destX = mBoundsInScreen.left + mEdgePaddingLeft;
                    } else {
                        posX = Gravity.RIGHT;
                        destX = mBoundsInScreen.right - mEdgePaddingRight - mFloatView.getWidth();
                    }
                    if (mFloatButtonCallback != null) {
                        float posY = 0;
                        if (mBoundsInScreen.height() - mFloatView.getHeight() != 0) {
                            posY = 1f * (mFloatViewWindowParam.y - mBoundsInScreen.top) / (mBoundsInScreen.height() - mFloatView.getHeight());
                        }
                        mFloatButtonCallback.onPositionChanged(destX, mFloatViewWindowParam.y, posX, posY);
                    }
                    int deltaHorizon = destX - x;
                    //小于100直接移动 否则开启动画
                    if (Math.abs(deltaHorizon) < 100) {
                        mFloatViewWindowParam.x = destX;
                        mFloatView.requestLayout();
                    } else {
                        ValueAnimator animator = ValueAnimator.ofInt(x, destX);
                        animator.setInterpolator(mInterpolator);
                        if (mUpdateListener == null) {
                            mUpdateListener = new FloatAnimatorUpdateListener();
                            mUpdateListener.setUpdateView(FloatTouchListener.this);
                        }
                        animator.addUpdateListener(mUpdateListener);
                        animator.setDuration(200);
                        animator.start();
                    }
                }
                resetStatus();
                break;
            }
        }
        return result;
    }


    private void resetStatus() {
        mDownPointerId = -1;
        mPreviousX = -1;
        mPreviousY = -1;
        mHasMoved = false;
    }

    public void setFloatButtonCallback(FloatButtonCallback floatButtonCallback) {
        this.mFloatButtonCallback = floatButtonCallback;
    }

    private class FloatAnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {

        private WeakReference<FloatTouchListener> mListener;

        public void setUpdateView(FloatTouchListener listener) {
            mListener = new WeakReference<FloatTouchListener>(listener);
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            Integer value = (Integer) animation.getAnimatedValue();
            FloatTouchListener listener = null;
            if (mListener == null || (listener = mListener.get()) == null) {
                return;
            }
            listener.mFloatViewWindowParam.x = value;
            mFloatView.requestLayout();
        }
    }

    /**
     * 触摸监听回调
     */
    public interface FloatButtonCallback {
        void onPositionChanged(int x, int y, int gravityX, float percentY);

        void onTouch();
    }
}
