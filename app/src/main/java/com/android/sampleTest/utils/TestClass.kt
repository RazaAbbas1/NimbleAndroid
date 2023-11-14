package com.android.sampleTest.utils

import com.android.network.services.ApiService
import javax.inject.Inject

/**
 * Created By Khizzar
 * Created On 11/11/2023
 **/
class TestClass @Inject constructor(
    private val apiService: ApiService
) {
    fun getApiService() : ApiService {
        return apiService
    }
}