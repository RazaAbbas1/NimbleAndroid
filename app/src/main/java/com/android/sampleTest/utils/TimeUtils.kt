package com.android.sampleTest.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 * Created By Khizzar
 * Created On 11/10/2023
 **/

object TimeUtils {

    fun getTimeFromSeconds(seconds: Int): String {

        val hoursPassed = seconds / 3600
        return if(hoursPassed > 0){
            String.format("%02d:%02d:%02d", (seconds / 3600), ((seconds % 3600) / 60), (seconds % 60))
        } else {
            String.format("%02d:%02d", ((seconds % 3600) / 60), (seconds % 60))
        }

    }

    fun getCurrentDate(): String {
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("EEE, MMM d")
        return current.format(formatter)
    }

}