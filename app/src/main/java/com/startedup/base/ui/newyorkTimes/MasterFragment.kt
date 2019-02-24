package com.startedup.base.ui.newyorkTimes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.startedup.base.R
import com.startedup.base.di.Injectable
import kotlinx.android.synthetic.main.master_fragment.*



class MasterFragment : Fragment(),Injectable {
    private lateinit var storiesPagerAdapter:StoriesPagerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.master_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()
    }

    private fun initViewPager() {
        storiesPagerAdapter = StoriesPagerAdapter(childFragmentManager)
        viewPager.adapter = storiesPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        viewPager.offscreenPageLimit = 6
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                MasterFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

}
