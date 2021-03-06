package com.startedup.base.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.startedup.base.model.times.TimesStoriesResponse

@Dao
interface TimesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(timesStoriesResponse: TimesStoriesResponse)

    @Query("SELECT * FROM times_stories_response WHERE section IN(:section) ")
     fun load(section:String): LiveData<TimesStoriesResponse>
}