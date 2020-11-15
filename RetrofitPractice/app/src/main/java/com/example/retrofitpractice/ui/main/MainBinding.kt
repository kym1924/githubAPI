package com.example.retrofitpractice.ui.main

import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.example.retrofitpractice.util.addMainPagerListener
import com.example.retrofitpractice.util.addTabLayoutListener
import com.google.android.material.tabs.TabLayout

object MainBinding {
    @BindingAdapter("mainAdapter")
    @JvmStatic
    fun mainAdapter(viewPager: ViewPager, fragmentManager: FragmentManager) {
        viewPager.adapter =
            MainPagerAdapter(fragmentManager)
    }

    @BindingAdapter("setTabListener")
    @JvmStatic
    fun setTabListener(tabLayout : TabLayout, viewPager : ViewPager) {
        tabLayout.addTabLayoutListener(viewPager)
    }

    @BindingAdapter("setUpWithViewPager")
    @JvmStatic
    fun setUpWithViewPager(viewPager : ViewPager, tabLayout : TabLayout) {
        viewPager.addMainPagerListener(tabLayout)
    }

    @BindingAdapter("setTabItems")
    @JvmStatic
    fun setTabItems(tabLayout : TabLayout, tabItems : String) {
        tabLayout.addTab(tabLayout.newTab().setText(tabItems.split(" ")[0]))
        tabLayout.addTab(tabLayout.newTab().setText(tabItems.split(" ")[1]))
    }
}