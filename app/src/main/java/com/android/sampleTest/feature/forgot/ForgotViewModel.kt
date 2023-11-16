package com.android.sampleTest.feature.forgot

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.android.network.models.ForgotPostModel
import com.android.sampleTest.base.BaseViewModel
import com.android.sampleTest.repositories.MainRepository
import com.android.network.models.User
import com.android.network.services.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ForgotViewModel @Inject constructor(private val repository: MainRepository) : BaseViewModel() {

    val email: ObservableField<String> = ObservableField("")

    private val _mForgotFLow = MutableStateFlow<ForgotEvent>(ForgotEvent.Empty)
    val forgotFLow: StateFlow<ForgotEvent> = _mForgotFLow

    fun resetApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
            _mForgotFLow.value = ForgotEvent.Loading
            when(val forgotResponse = repository.forgot(ForgotPostModel(user = User(email.get().toString())))) {
                is ApiResult.Error -> _mForgotFLow.value = ForgotEvent.Failure(forgotResponse.message!!)
                is ApiResult.Success -> {
                    val response = forgotResponse.data?.meta
                    if(response == null) {
                        _mForgotFLow.value = ForgotEvent.Failure("Unexpected error")
                    } else {
                        _mForgotFLow.value = ForgotEvent.Success(
                            response
                        )
                    }
                }
            }
        }
    }

}