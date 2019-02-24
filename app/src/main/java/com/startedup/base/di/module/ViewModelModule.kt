package com.startedup.base.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.startedup.base.di.ViewModelFactory
import com.startedup.base.di.ViewModelKey
import com.startedup.base.ui.newyorkTimes.TimesViewModel


import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TimesViewModel::class)
    abstract fun bindUserViewModel(timesViewModel: TimesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}