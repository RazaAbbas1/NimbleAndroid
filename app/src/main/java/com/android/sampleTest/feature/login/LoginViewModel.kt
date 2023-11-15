package com.android.sampleTest.feature.login

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.android.sampleTest.base.BaseViewModel
import com.android.sampleTest.repositories.MainRepository
import com.android.network.models.LoginPostModel
import com.android.network.models.RefreshTokenPostModel
import com.android.network.models.SurveysResponseModel
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

    val userEmail: ObservableField<String> = ObservableField("")
    val password: ObservableField<String> = ObservableField("")

    private val _mLoginFlow = MutableStateFlow<LoginEvent>(LoginEvent.Empty)
    val loginFLow: StateFlow<LoginEvent> = _mLoginFlow

    fun loginApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
            _mLoginFlow.value = LoginEvent.Loading
            when(val loginResponse = repository.login(LoginPostModel(email =  userEmail.get().toString(), password = password.get().toString()))) {
                is ApiResult.Error -> _mLoginFlow.value = LoginEvent.Failure(loginResponse.message!!)
                is ApiResult.Success -> {
                    val response = loginResponse.data!!.data?.attributes
                    if(response == null) {
                        _mLoginFlow.value = LoginEvent.Failure("Unexpected error")
                    } else {
                        _mLoginFlow.value = LoginEvent.Success(
                            response
                        )
                    }
                }
            }
        }
    }



    fun refreshTokenApiCall(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _mLoginFlow.value = LoginEvent.Loading
            when(val loginResponse = repository.refreshToken(RefreshTokenPostModel(refreshToken = token))) {
                is ApiResult.Error -> _mLoginFlow.value = LoginEvent.Failure(loginResponse.message!!)
                is ApiResult.Success -> {
                    val response = loginResponse.data!!.data?.attributes
                    if(response == null) {
                        _mLoginFlow.value = LoginEvent.Failure("Unexpected error")
                    } else {
                        _mLoginFlow.value = LoginEvent.Success(
                            response
                        )
                    }
                }
            }
        }
    }

}