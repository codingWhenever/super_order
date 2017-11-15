package com.e_at.super_order.mvp.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.e_at.eatlibrary.injection.From;
import com.e_at.eatlibrary.widget.NonScrollViewPager;
import com.e_at.super_order.R;
import com.e_at.super_order.mvp.ui.adapter.ViewPagerAdapter;
import com.e_at.super_order.mvp.ui.fragment.BaseFragment;
import com.lzy.widget.AlphaIndicator;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * HomeActivity
 *
 * @author leo
 * @desc 主页
 * @date 2017/11/15
 * @email lei.lu@e-at.com
 */
public class HomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private List<Fragment> mFragments;
    private BaseFragment oneFragment;
    private BaseFragment twoFragment;
    private BaseFragment threeFragment;
    private BaseFragment fourFragment;
    @From(R.id.viewpager)
    private NonScrollViewPager mViewPager;
    @From(R.id.alphaIndicator)
    private AlphaIndicator mIndicator;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showDefaultPage(int type) {

    }

    @Override
    public void retry() {

    }

    @Override
    public void showLoading(@Nullable String loadingMessage) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initTab();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.setFragmentList(mFragments);
        mViewPager.setAdapter(adapter);

        mIndicator.setViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(3);
        //不支持滑动
        mViewPager.setScroll(false);
    }

    private void initTab() {
        mFragments = new ArrayList<>();
        oneFragment = new BaseFragment();
        twoFragment = new BaseFragment();
        threeFragment = new BaseFragment();
        fourFragment = new BaseFragment();
        mFragments.add(oneFragment);
        mFragments.add(twoFragment);
        mFragments.add(threeFragment);
        mFragments.add(fourFragment);
    }

    @Override
    public boolean setImmersive() {
        return true;
    }

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
