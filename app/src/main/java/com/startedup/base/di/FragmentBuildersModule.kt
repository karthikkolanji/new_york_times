package com.startedup.base.di

import com.startedup.base.ui.newyorkTimes.MasterFragment
import com.startedup.base.ui.newyorkTimes.TimesDetailsFragment
import com.startedup.base.ui.newyorkTimes.TimesListFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMasterFragment(): MasterFragment

    @ContributesAndroidInjector
    abstract fun contributeTimesListFragment(): TimesListFragment

    @ContributesAndroidInjector
    abstract fun contributeTimesDetailsFragment(): TimesDetailsFragment

}