package com.startedup.base.model.times


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "multi_media_item")
data class MultimediaItem (

        @PrimaryKey var id:Int,

        @field:SerializedName("copyright")
    var copyright: String? = null,

        @field:SerializedName("subtype")
    var subtype: String? = null,

        @field:SerializedName("format")
    var format: String? = null,

        @field:SerializedName("width")
    var width: Int = 0,

        @field:SerializedName("caption")
    var caption: String? = null,

        @field:SerializedName("type")
    var type: String? = null,

        @field:SerializedName("url")
    var url: String? = null,

        @field:SerializedName("height")
    var height: Int = 0
)