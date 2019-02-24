package com.startedup.base.model.times


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.startedup.base.db.typeConverters.ResultsConverters

@Entity(tableName = "times_stories_response")
@TypeConverters(ResultsConverters::class)
data class  TimesStoriesResponse (

        @PrimaryKey var id:Int,

    @field:SerializedName("copyright")
    var copyright: String? = null,

    @field:SerializedName("last_updated")
    var lastUpdated: String? = null,

    @field:SerializedName("section")
    var section: String? = null,

    @field:SerializedName("results")
    var results: List<ResultsItem>? = null,

    @field:SerializedName("num_results")
    var numResults: Int = 0,

    @field:SerializedName("status")
    var status: String? = null
)