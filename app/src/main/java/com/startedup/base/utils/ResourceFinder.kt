package com.startedup.base.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v4.content.ContextCompat

import com.startedup.base.ui.MyApp
import javax.inject.Inject

class ResourceFinder @Inject constructor(private val context: Context) {


    //--------------------------------- Color ------------------------------------------------------
    fun getColour(colorId: Int): Int {
        return ContextCompat.getColor(context, colorId)
    }


    //--------------------------------- Drawable ---------------------------------------------------
    fun getDrawable(drawableId: Int): Drawable {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.resources.getDrawable(drawableId, null)
        } else {
            context.resources.getDrawable(drawableId)
        }
    }


    //--------------------------------- Dimension --------------------------------------------------
    fun getDimension(dimensionId: Int): Int {
        return context.resources.getDimension(dimensionId).toInt()
    }


    //--------------------------------- String -----------------------------------------------------
    fun getString(stringId: Int): String {
        return context.getString(stringId)
    }


}
