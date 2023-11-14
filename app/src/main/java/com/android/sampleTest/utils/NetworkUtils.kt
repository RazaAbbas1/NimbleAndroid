package com.android.sampleTest.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created By Khizzar
 * Created On 11/10/2023
 **/

fun hasNetworkAvailable(context: Context): Boolean {
    val service = Context.CONNECTIVITY_SERVICE
    val manager = context.getSystemService(service) as ConnectivityManager?
    val network = manager?.activeNetworkInfo
    return (network != null)
}

fun isInternetAvailable(context: Context): Boolean {
    return ConnectivityStatus(context).isConnected()
}
