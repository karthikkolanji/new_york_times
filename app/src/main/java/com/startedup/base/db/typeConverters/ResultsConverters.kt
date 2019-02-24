package com.startedup.base.db.typeConverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.startedup.base.model.times.ResultsItem


class ResultsConverters {

        @TypeConverter
        fun listToJson(value: List<ResultsItem>?): String {

            return Gson().toJson(value)
        }

        @TypeConverter
        fun jsonToList(value: String): List<ResultsItem>? {

            val objects = Gson().fromJson(value, Array<ResultsItem>::class.java) as Array<ResultsItem>
            return objects.toList()
        }

}