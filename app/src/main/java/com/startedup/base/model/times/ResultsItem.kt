package com.startedup.base.model.times


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.startedup.base.db.typeConverters.FacetConverters
import com.startedup.base.db.typeConverters.MultiMediaConverters
import com.startedup.base.db.typeConverters.ResultsConverters

@Entity(tableName = "results_item")
@TypeConverters(FacetConverters::class,MultiMediaConverters::class)
data class ResultsItem (

        @PrimaryKey var id:Int,

        @field:SerializedName("per_facet")
    var perFacet: List<String>? = null,

        @field:SerializedName("subsection")
    var subsection: String? = null,

        @field:SerializedName("item_type")
    var itemType: String? = null,

        @field:SerializedName("org_facet")
    var orgFacet: List<String>? = null,

        @field:SerializedName("section")
    var section: String? = null,

        @field:SerializedName("abstract")
    var jsonMemberAbstract: String? = null,

        @field:SerializedName("title")
    var title: String? = null,

        @field:SerializedName("des_facet")
    var desFacet: List<String>? = null,

        @field:SerializedName("url")
    var url: String? = null,

        @field:SerializedName("short_url")
    var shortUrl: String? = null,

        @field:SerializedName("material_type_facet")
    var materialTypeFacet: String? = null,

        @field:SerializedName("multimedia")
    var multimedia: List<MultimediaItem>? = null,

        @field:SerializedName("geo_facet")
    var geoFacet: List<String>? = null,

        @field:SerializedName("updated_date")
    var updatedDate: String? = null,

        @field:SerializedName("created_date")
    var createdDate: String? = null,

        @field:SerializedName("byline")
    var byline: String? = null,

        @field:SerializedName("published_date")
    var publishedDate: String? = null,

        @field:SerializedName("kicker")
    var kicker: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            TODO("multimedia"),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeStringList(perFacet)
        parcel.writeString(subsection)
        parcel.writeString(itemType)
        parcel.writeStringList(orgFacet)
        parcel.writeString(section)
        parcel.writeString(jsonMemberAbstract)
        parcel.writeString(title)
        parcel.writeStringList(desFacet)
        parcel.writeString(url)
        parcel.writeString(shortUrl)
        parcel.writeString(materialTypeFacet)
        parcel.writeStringList(geoFacet)
        parcel.writeString(updatedDate)
        parcel.writeString(createdDate)
        parcel.writeString(byline)
        parcel.writeString(publishedDate)
        parcel.writeString(kicker)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResultsItem> {
        override fun createFromParcel(parcel: Parcel): ResultsItem {
            return ResultsItem(parcel)
        }

        override fun newArray(size: Int): Array<ResultsItem?> {
            return arrayOfNulls(size)
        }
    }
}