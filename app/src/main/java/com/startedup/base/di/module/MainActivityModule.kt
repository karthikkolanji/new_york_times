package com.startedup.base.di.module

import com.startedup.base.di.FragmentBuildersModule
import com.startedup.base.ui.newyorkTimes.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}