package com.android.sampleTest.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowManager

/**
 * Created By Khizzar
 * Created On 11/10/2023
 **/

object ViewUtils {

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setStatusBarGradient(activity: Activity, toSetDrawable: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = activity.window
            val background = activity.resources.getDrawable(toSetDrawable, null)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = activity.resources.getColor(android.R.color.transparent)
            window.navigationBarColor = activity.resources.getColor(android.R.color.transparent)
            window.setBackgroundDrawable(background)
        }
    }

}