package com.android.network.services

import com.android.network.models.LoginPostModel
import com.android.network.models.LoginResponseModel
import retrofit2.http.Body

import retrofit2.http.POST




/**
 * Created By Khizzar
 * Created On 11/11/2023
 **/
interface ApiService {

    @POST("/api/v1/oauth/token")
    fun login(@Body loginPostData: LoginPostModel): LoginResponseModel
}