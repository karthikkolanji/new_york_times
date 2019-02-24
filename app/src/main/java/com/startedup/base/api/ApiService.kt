package com.startedup.base.api

import android.arch.lifecycle.LiveData


import com.startedup.base.model.movies.TopRatedMovieResponse
import com.startedup.base.model.times.TimesStoriesResponse

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Observable<TopRatedMovieResponse>

    @GET("svc/topstories/v2/{section}.json?")
    fun getTopStories(@Path ("section") section:String,@Query("api-key") apiKey:String):LiveData<ApiResponse<TimesStoriesResponse>>
}
