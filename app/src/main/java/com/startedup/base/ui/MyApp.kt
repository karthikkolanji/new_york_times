package com.startedup.base.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.startedup.base.di.AppInjector

import com.startedup.base.utils.AppExceptionHandler
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

import timber.log.Timber
import javax.inject.Inject

class MyApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AppInjector.init(this)
        setupCrashHandler()
    }

    private fun setupCrashHandler() {
        // 1. Get the system handler.
        val systemHandler = Thread.getDefaultUncaughtExceptionHandler()

        // 2. Set the default handler as a dummy (so that crashlytics fallbacks to this one, once set)
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable -> /* do nothing */ }

        // 3. Setup crashlytics so that it becomes the default handler (and fallbacking to our dummy handler)
        //Fabric.with(this, Crashlytics())

        val fabricExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()

        // 4. Setup our handler, which tries to restart the app.
        Thread.setDefaultUncaughtExceptionHandler(AppExceptionHandler(systemHandler, fabricExceptionHandler, this))
    }

    override fun activityInjector() = dispatchingAndroidInjector


}
