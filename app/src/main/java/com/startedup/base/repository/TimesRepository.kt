package com.startedup.base.repository

import android.arch.lifecycle.LiveData
import android.provider.SyncStateContract
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.util.Log
import com.startedup.base.api.ApiService
import com.startedup.base.api.Resource
import javax.inject.Inject
import com.startedup.base.api.ApiResponse
import com.startedup.base.api.NetworkBoundResource
import com.startedup.base.constants.ConfigConstant
import com.startedup.base.db.TimesDao
import com.startedup.base.model.times.TimesStoriesResponse
import com.startedup.base.utils.AppExecutors
import com.startedup.base.utils.RateLimiter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


class TimesRepository @Inject constructor(private val apiService: ApiService,
                                          private val timesDao: TimesDao,
                                          private val appExecutors: AppExecutors) {

    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun loadStories(section:String): LiveData<Resource<TimesStoriesResponse>> {
        return object : NetworkBoundResource<TimesStoriesResponse, TimesStoriesResponse>(appExecutors) {
            override fun saveCallResult(item: TimesStoriesResponse) {
                timesDao.insert(item)
            }

            override fun shouldFetch(data: TimesStoriesResponse?): Boolean {
                //return data == null  || repoListRateLimit.shouldFetch(section)
                return true
            }

            override fun loadFromDb() = timesDao.load(section)

            override fun createCall() = apiService.getTopStories(section,ConfigConstant.TIMES_KEY)

            override fun onFetchFailed() {
                repoListRateLimit.reset(section)
            }
        }.asLiveData()
    }
}