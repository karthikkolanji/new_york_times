package com.startedup.base.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.startedup.base.db.TimesDao
import com.startedup.base.db.TimesDataBase
import com.startedup.base.ui.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(context: Application): TimesDataBase {
        return Room.databaseBuilder(context, TimesDataBase::class.java, "TimesDB").build()
    }


    @Provides
    @Singleton
    fun provideDao(timesDataBase: TimesDataBase): TimesDao {
        return timesDataBase.timesDao()
    }

}