package com.retrofit.github.util

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

fun ViewPager.addMainPagerListener(tabLayout : TabLayout){
    this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {}

        override fun onPageSelected(position: Int) { tabLayout.getTabAt(position)?.select() }
    })
}