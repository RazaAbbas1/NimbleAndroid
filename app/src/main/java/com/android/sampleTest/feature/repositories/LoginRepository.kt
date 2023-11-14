package com.android.sampleTest.feature.repositories

import com.android.network.models.LoginPostModel
import com.android.network.models.LoginResponseModel
import com.android.network.services.ApiResult
import com.android.network.services.ApiService
import javax.inject.Inject


interface MainRepository {
    suspend fun login(model: LoginPostModel): ApiResult<LoginResponseModel>
}

class LoginRepository @Inject constructor(
    private val apiService: ApiService
):MainRepository {
    override suspend fun login(model: LoginPostModel): ApiResult<LoginResponseModel> {
        return try {
            val response = apiService.login(model)
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