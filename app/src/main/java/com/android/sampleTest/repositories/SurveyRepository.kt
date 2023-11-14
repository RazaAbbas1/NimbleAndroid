package com.android.sampleTest.repositories

import com.android.network.models.SurveysResponseModel
import com.android.network.services.ApiResult
import com.android.network.services.ApiService
import com.android.sampleTest.session.SessionManager
import javax.inject.Inject


interface SurveyDataRepository {
    suspend fun getSurveysList(token: String): ApiResult<SurveysResponseModel>
}

class SurveyRepository @Inject constructor(
    private val apiService: ApiService, private val sessionManager: SessionManager
): SurveyDataRepository {
    override suspend fun getSurveysList(token: String): ApiResult<SurveysResponseModel> {
        return try {
            val response = apiService.getSurveysList(token = "${sessionManager.loginInfo!!.tokenType} ${sessionManager.loginInfo!!.accessToken}")
            if (response.data != null){
                ApiResult.Success(response)
            }else{
                ApiResult.Error("An Error occurred")
            }
        }catch (e:Exception){
            ApiResult.Error("An $e occurred")
        }
    }


}