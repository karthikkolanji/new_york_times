package com.startedup.base.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.startedup.base.model.times.MultimediaItem
import com.startedup.base.model.times.ResultsItem
import com.startedup.base.model.times.TimesStoriesResponse

@Database(entities = [TimesStoriesResponse::class, ResultsItem::class, MultimediaItem::class],
        version = 1)


abstract class TimesDataBase : RoomDatabase() {

    abstract fun timesDao(): TimesDao
}