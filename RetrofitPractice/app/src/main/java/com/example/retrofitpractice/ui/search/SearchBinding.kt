package com.example.retrofitpractice.ui.search

import android.view.View
import androidx.databinding.BindingAdapter

object SearchBinding {
    @BindingAdapter("visibleResetButton")
    @JvmStatic
    fun visibleResetButton(view : View, search : String) {
        view.visibility = if (search.isEmpty()) View.INVISIBLE else View.VISIBLE
    }
}