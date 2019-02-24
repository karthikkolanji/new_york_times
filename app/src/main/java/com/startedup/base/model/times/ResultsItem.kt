package com.startedup.base.model.times


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
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
)