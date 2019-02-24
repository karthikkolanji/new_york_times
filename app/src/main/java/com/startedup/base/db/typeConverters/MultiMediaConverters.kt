package com.startedup.base.db.typeConverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.startedup.base.model.times.MultimediaItem
import com.startedup.base.model.times.ResultsItem


class MultiMediaConverters {

        @TypeConverter
        fun listToJson(value: List<MultimediaItem>?): String {

            return Gson().toJson(value)
        }

        @TypeConverter
        fun jsonToList(value: String): List<MultimediaItem>? {

            val objects = Gson().fromJson(value, Array<MultimediaItem>::class.java) as Array<MultimediaItem>
            return objects.toList()
        }

}