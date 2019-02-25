package com.startedup.base.model.times


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(copyright)
        parcel.writeString(subtype)
        parcel.writeString(format)
        parcel.writeInt(width)
        parcel.writeString(caption)
        parcel.writeString(type)
        parcel.writeString(url)
        parcel.writeInt(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MultimediaItem> {
        override fun createFromParcel(parcel: Parcel): MultimediaItem {
            return MultimediaItem(parcel)
        }

        override fun newArray(size: Int): Array<MultimediaItem?> {
            return arrayOfNulls(size)
        }
    }
}