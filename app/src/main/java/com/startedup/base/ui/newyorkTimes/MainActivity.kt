package com.startedup.base.ui.newyorkTimes

import android.os.Bundle
import android.support.v4.app.Fragment
import com.startedup.base.R
import com.startedup.base.ui.base.BaseActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity:BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.times_activity)
        //replaceFragment(MasterFragment.newInstance(),R.id.fragment_container,false)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onMultiplePermissionGranted() {

    }

    override fun onSinglePermissionGranted() {

    }

    override fun onNetworkOn() {

    }

    override fun onNetworkOff() {

    }
}