package com.android.sampleTest.feature.login

import androidx.lifecycle.viewModelScope
import com.android.sampleTest.base.BaseViewModel
import com.android.sampleTest.feature.repositories.MainRepository
import com.android.network.models.LoginPostModel
import com.android.network.services.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: MainRepository) : BaseViewModel() {

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

}