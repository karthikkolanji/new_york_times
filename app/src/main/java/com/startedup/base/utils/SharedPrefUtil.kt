package com.startedup.base.utils

import android.content.Context
import android.content.SharedPreferences

import com.startedup.base.ui.MyApp
import javax.inject.Inject

/**
 * Created by karthik on 09/6/18.
 */

class SharedPrefUtil @Inject constructor(private val context: Context) {

    private val SHARED_PREF_FILE_NAME = "BaseApp"
    private var mSharedPreferences: SharedPreferences? = null
    private var mEditor: SharedPreferences.Editor? = null

    private val DEFAULT_INT = 0
    private val DEFAULT_STRING = ""
    private val DEFAULT_BOOLEAN = false
    private val DEFAULT_LONG: Long = 0


    val instance: SharedPreferences?
        get() {
            if (mSharedPreferences == null) {
                mSharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
                mEditor = mSharedPreferences!!.edit()
            }
            return mSharedPreferences

        }

    // ------------------------------------------ int ---------------------------------------------------------------------
    fun put(key: String, value: Int) {
        mEditor!!.putInt(key, value)
        mEditor!!.apply()
    }

    fun getInt(key: String): Int {
        return instance!!.getInt(key, DEFAULT_INT)
    }


    // ------------------------------------------ String ---------------------------------------------------------------------
    fun put(key: String, value: String) {
        mEditor!!.putString(key, value)
        mEditor!!.apply()
    }

    fun getString(key: String): String? {
        return instance!!.getString(key, DEFAULT_STRING)
    }

    // ------------------------------------------ boolean ---------------------------------------------------------------------
    fun put(key: String, value: Boolean) {

        mEditor!!.putBoolean(key, value)
        mEditor!!.apply()
    }

    fun getBoolean(key: String): Boolean {
        return instance!!.getBoolean(key, DEFAULT_BOOLEAN)
    }


    // ------------------------------------------ long ---------------------------------------------------------------------
    fun put(key: String, value: Long) {

        mEditor!!.putLong(key, value)
        mEditor!!.apply()
    }

    fun getLong(key: String): Long {
        return instance!!.getLong(key, DEFAULT_LONG)
    }

    // ------------------------------------------ clearing keys ---------------------------------------------------------------------
    fun remove(key: String) {

        mEditor!!.remove(key)
        mEditor!!.apply()
    }

    fun clearAll() {
        mEditor!!.clear()
        mEditor!!.apply()
    }

}

