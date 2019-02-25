package com.startedup.base.ui.newyorkTimes

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.startedup.base.api.Resource

import com.startedup.base.model.times.TimesStoriesResponse
import com.startedup.base.repository.TimesRepository
import javax.inject.Inject

class TimesViewModel @Inject constructor(private val timesRepository: TimesRepository) :
        ViewModel() {
    lateinit var data: LiveData<Resource<TimesStoriesResponse>>
    fun fetchStories(section:String): LiveData<Resource<TimesStoriesResponse>> {
        data = timesRepository.loadStories(section)
        return data
    }

    override fun onCleared() {
        super.onCleared()
    }
}