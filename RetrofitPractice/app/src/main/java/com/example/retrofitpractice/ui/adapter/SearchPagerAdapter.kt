package com.example.retrofitpractice.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.retrofitpractice.ui.repo.RepoFragment
import com.example.retrofitpractice.ui.user.UserFragment

class SearchPagerAdapter (fm : FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position:Int) : Fragment {
        return when(position) {
            0-> UserFragment()
            else-> RepoFragment()
        }
    }
    override fun getCount() = 2
}