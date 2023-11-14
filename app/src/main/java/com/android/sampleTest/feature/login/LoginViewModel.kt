package com.android.sampleTest.feature.login

import androidx.lifecycle.viewModelScope
import com.android.sampleTest.base.BaseViewModel
import com.android.sampleTest.repositories.MainRepository
import com.android.network.models.LoginPostModel
import com.android.network.models.RefreshTokenPostModel
import com.android.network.services.ApiResult
import com.android.sampleTest.feature.survey.SurveyEvent
import com.android.sampleTest.repositories.SurveyDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: MainRepository,
                                        private val surveyDataRepository: SurveyDataRepository) : BaseViewModel() {

    private val _conversion = MutableStateFlow<LoginEvent>(LoginEvent.Empty)
    val conversion: StateFlow<LoginEvent> = _conversion

    fun loginApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
            _conversion.value = LoginEvent.Loading
            when(val loginResponse = repository.login(LoginPostModel())) {
                is ApiResult.Error -> _conversion.value = LoginEvent.Failure(loginResponse.message!!)
                is ApiResult.Success -> {
                    val response = loginResponse.data!!.data?.attributes
                    if(response == null) {
                        _conversion.value = LoginEvent.Failure("Unexpected error")
                    } else {
                        _conversion.value = LoginEvent.Success(
                            response
                        )
                    }
                }
            }
        }
    }



    fun refreshTokenApiCall(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _conversion.value = LoginEvent.Loading
            when(val loginResponse = repository.refreshToken(RefreshTokenPostModel(refreshToken = token))) {
                is ApiResult.Error -> _conversion.value = LoginEvent.Failure(loginResponse.message!!)
                is ApiResult.Success -> {
                    val response = loginResponse.data!!.data?.attributes
                    if(response == null) {
                        _conversion.value = LoginEvent.Failure("Unexpected error")
                    } else {
                        _conversion.value = LoginEvent.Success(
                            response
                        )
                    }
                }
            }
        }
    }


    private val _mSurveysFlow = MutableStateFlow<SurveyEvent>(SurveyEvent.Empty)
    val surveyDataFlow: StateFlow<SurveyEvent> = _mSurveysFlow

    fun fetchSurveys(){
        viewModelScope.launch(Dispatchers.IO) {
            _mSurveysFlow.value = SurveyEvent.Loading
            when(val response = surveyDataRepository.getSurveysList("")) {
                is ApiResult.Error -> _mSurveysFlow.value = SurveyEvent.Failure(response.message!!)
                is ApiResult.Success -> {
                    val responseData = response.data!!.data
                    if(responseData == null) {
                        _mSurveysFlow.value = SurveyEvent.Failure("Unexpected error")
                    } else {
                        _mSurveysFlow.value = SurveyEvent.Success(
                            responseData
                        )
                    }
                }
            }
        }
    }

}