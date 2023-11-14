package com.android.network.services

import com.android.network.Constants.GET_SURVEYS_URL
import com.android.network.Constants.LOGIN_URL
import com.android.network.Constants.REFRESH_TOKEN_URL
import com.android.network.models.LoginPostModel
import com.android.network.models.LoginResponseModel
import com.android.network.models.RefreshTokenPostModel
import com.android.network.models.SurveysResponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

import retrofit2.http.POST




/**
 * Created By Khizzar
 * Created On 11/11/2023
 **/
interface ApiService {

    @POST(LOGIN_URL)
    fun login(@Body loginPostData: LoginPostModel): LoginResponseModel

    @POST(REFRESH_TOKEN_URL)
    fun refreshToken(@Body postData: RefreshTokenPostModel): LoginResponseModel

    @GET(GET_SURVEYS_URL)
    fun getSurveysList(@Header("Authorization") token: String): SurveysResponseModel
}