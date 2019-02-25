package com.startedup.base.ui.newyorkTimes

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class StoriesPagerAdapter(fragmentManager: FragmentManager?)
    :FragmentStatePagerAdapter(fragmentManager){

    private val sections= arrayListOf("science","technology","business","world","movies","travel")
    //private val sections= arrayListOf("science")

    override fun getItem(position: Int): Fragment {
        return TimesListFragment.newInstance(sections[position])
    }

    override fun getCount(): Int {
        return sections.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return sections[position]
    }

}