package com.startedup.base.di.module

import com.startedup.base.api.ApiService
import com.startedup.base.db.TimesDao
import com.startedup.base.repository.TimesRepository
import com.startedup.base.utils.AppExecutors
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {


    @Provides
    @Singleton
    fun provideTimesRepository(apiService: ApiService, timesDao: TimesDao,appExecutors: AppExecutors)
            :TimesRepository {
        return TimesRepository(apiService, timesDao,appExecutors)
    }
}