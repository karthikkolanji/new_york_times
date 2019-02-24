package com.startedup.base.ui.newyorkTimes

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class StoriesPagerAdapter(fragmentManager: FragmentManager?)
    :FragmentStatePagerAdapter(fragmentManager){

    private val sections= arrayListOf("science","technology","business","world","movies","travel")

    override fun getItem(position: Int): Fragment {

        //val bundle=Bundle()
        //bundle.putString("section",sections[position])
        //Navigation.createNavigateOnClickListener(R.id.action_masterFragment_to_timesListFragment,bundle)
//        return when(position){
//            0-> TimesListFragment.newInstance("science")
//            1-> TimesListFragment.newInstance("technology")
//            2-> TimesListFragment.newInstance("business")
//            3-> TimesListFragment.newInstance("world")
//            4-> TimesListFragment.newInstance("travel")
//            else-> TimesListFragment.newInstance("movies")
//        }
        return TimesListFragment.newInstance(sections[position])
    }

    override fun getCount(): Int {

        return sections.size

    }


    override fun getPageTitle(position: Int): CharSequence? {
        return sections[position]
    }
}