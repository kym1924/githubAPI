package com.retrofit.github.ui.search

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.retrofit.github.data.model.Search
import com.retrofit.github.databinding.ItemSearchHistoryBinding
import com.retrofit.github.ui.adapter.SearchAdapter
import com.retrofit.github.ui.adapter.SearchPagerAdapter
import com.retrofit.github.util.addMainPagerListener
import com.retrofit.github.util.addTabLayoutListener

object SearchBinding {
    @BindingAdapter("visibleResetButton")
    @JvmStatic
    fun visibleResetButton(view : View, search : String) {
        view.visibility = if (search.isEmpty()) View.INVISIBLE else View.VISIBLE
    }

    @BindingAdapter("mainAdapter")
    @JvmStatic
    fun mainAdapter(viewPager: ViewPager, fragmentManager: FragmentManager) {
        viewPager.adapter = SearchPagerAdapter(fragmentManager)
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

    @BindingAdapter("setAdapter")
    @JvmStatic
    fun setAdapter(recyclerView : RecyclerView, searchViewModel : SearchViewModel) {
        val adapter = SearchAdapter<ItemSearchHistoryBinding>(searchViewModel)
        recyclerView.adapter = adapter
    }

    @BindingAdapter("setListItem")
    @JvmStatic
    fun setListItem(recyclerView : RecyclerView, searchList : List<Search>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as SearchAdapter<*>) { searchList?.let{ setData(it) } }
    }
}