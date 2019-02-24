package com.startedup.base.listener

import com.startedup.base.model.times.TimesStoriesResponse

interface CallBacks{
    fun showError(isError:Boolean,errorMessage:String?)
    fun showLoading(isLoading:Boolean)
    fun showSuccessData(data: TimesStoriesResponse?)
    fun onRetry()
}