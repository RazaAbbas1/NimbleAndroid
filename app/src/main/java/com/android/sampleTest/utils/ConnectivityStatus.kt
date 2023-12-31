package com.android.sampleTest.utils

import android.content.Context
import android.content.ContextWrapper
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

/**
 * Created By Khizzar
 * Created On 11/10/2023
 **/
class ConnectivityStatus(val context: Context) : ContextWrapper(context) {

    fun isConnected(): Boolean {
        var result = false
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            result = true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            result = true
                        }
                    }
                }
            }
            else -> {
                val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
                if (activeNetwork != null) {
                    // connected to the internet
                    when {
                        activeNetwork.getType() === ConnectivityManager.TYPE_WIFI -> {
                            result = true
                        }
                        activeNetwork.getType() === ConnectivityManager.TYPE_MOBILE -> {
                            result = true
                        }
                    }
                }
            }
        }
        return result
    }
}
