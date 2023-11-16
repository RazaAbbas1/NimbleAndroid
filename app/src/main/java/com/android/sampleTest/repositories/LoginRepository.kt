package com.android.sampleTest.repositories

import com.android.network.models.ForgotPostModel
import com.android.network.models.ForgotResponseModel
import com.android.network.models.LoginPostModel
import com.android.network.models.LoginResponseModel
import com.android.network.models.RefreshTokenPostModel
import com.android.network.services.ApiResult
import com.android.network.services.ApiService
import javax.inject.Inject


interface MainRepository {
    suspend fun login(model: LoginPostModel): ApiResult<LoginResponseModel>

    suspend fun refreshToken(model: RefreshTokenPostModel): ApiResult<LoginResponseModel>

    suspend fun forgot(model: ForgotPostModel): ApiResult<ForgotResponseModel>

}

class LoginRepository @Inject constructor(
    private val apiService: ApiService
): MainRepository {
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

    override suspend fun refreshToken(model: RefreshTokenPostModel): ApiResult<LoginResponseModel> {

        return try {
            val response = apiService.refreshToken(model)
            if (response.data != null){
                ApiResult.Success(response)
            }else{
                ApiResult.Error("An Error occurred")
            }
        }catch (e:Exception){
            ApiResult.Error("An $e occurred")
        }
    }

    override suspend fun forgot(model: ForgotPostModel): ApiResult<ForgotResponseModel> {
        return try {
            val response = apiService.forgot(model)
            if (response.meta != null){
                ApiResult.Success(response)
            }else{
                ApiResult.Error("An Error occurred")
            }
        }catch (e:Exception){
            ApiResult.Error("An $e occurred")
        }
    }


}