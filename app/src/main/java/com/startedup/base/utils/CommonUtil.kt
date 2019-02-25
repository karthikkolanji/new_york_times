package com.startedup.base.utils

import android.content.Context
import android.support.v7.widget.CardView
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.startedup.base.R

class CommonUtil {



    companion object {

        fun showToasLong(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        fun showToasShort(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
