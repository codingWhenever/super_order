package com.e_at.eatlibrary.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * NonScrollViewPager
 *
 * @author leo
 * @desc 不支持滑动的viewpager，默认支持
 * @date 2017/11/15
 * @email lei.lu@e-at.com
 */
public class NonScrollViewPager extends ViewPager {
    private boolean supportScroll = true;

    public NonScrollViewPager(Context context) {
        super(context);
    }

    public NonScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScroll(boolean supportScroll) {
        this.supportScroll = supportScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev) && this.supportScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && this.supportScroll;
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item) {
        setCurrentItem(item, false);
    }
}
