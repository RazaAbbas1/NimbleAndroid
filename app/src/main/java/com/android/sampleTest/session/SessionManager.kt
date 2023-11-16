package com.android.sampleTest.session

import android.content.Context
import android.content.SharedPreferences
import com.android.network.models.Attributes
import com.android.sampleTest.R
import com.google.gson.Gson

/**
 * Session manager to save and fetch data from SharedPreferences
 */
class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_ATTRIBUTES = "user_attributes"
    }


    var loginInfo: Attributes?
        get(){
            val gson = Gson()
            val json = prefs.getString(USER_ATTRIBUTES, "")
            return gson.fromJson(json, Attributes::class.java)
        }
        set(loginObject) {
            val mEditor: SharedPreferences.Editor = prefs.edit()
            val gson = Gson()
            val json = gson.toJson(loginObject)
            mEditor.putString(USER_ATTRIBUTES, json)
            mEditor.apply()
        }


    /**
     * Function to save auth token
     */
    fun saveToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }


    fun clear(){
        prefs.edit().clear().apply()
    }
}