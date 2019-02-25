package com.startedup.base.model.times


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            TODO("results"),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(copyright)
        parcel.writeString(lastUpdated)
        parcel.writeString(section)
        parcel.writeInt(numResults)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TimesStoriesResponse> {
        override fun createFromParcel(parcel: Parcel): TimesStoriesResponse {
            return TimesStoriesResponse(parcel)
        }

        override fun newArray(size: Int): Array<TimesStoriesResponse?> {
            return arrayOfNulls(size)
        }
    }
}