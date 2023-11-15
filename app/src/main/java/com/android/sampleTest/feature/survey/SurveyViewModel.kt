package com.android.sampleTest.feature.survey

import androidx.lifecycle.viewModelScope
import com.android.sampleTest.base.BaseViewModel
import com.android.network.services.ApiResult
import com.android.sampleTest.repositories.SurveyDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SurveyViewModel @Inject constructor(private val surveyDataRepository: SurveyDataRepository) : BaseViewModel() {

    private val _mSurveysFlow = MutableStateFlow<SurveyEvent>(SurveyEvent.Empty)
    val surveyDataFlow: StateFlow<SurveyEvent> = _mSurveysFlow

    fun fetchSurveys(){
        viewModelScope.launch(Dispatchers.IO) {
            _mSurveysFlow.value = SurveyEvent.Loading
            when(val response = surveyDataRepository.getSurveysList("")) {
                is ApiResult.Error -> _mSurveysFlow.value = SurveyEvent.Failure(response.message!!)
                is ApiResult.Success -> {
                    response.data?.let {
                            _mSurveysFlow.value = SurveyEvent.Success(
                                it
                            )
                        } ?: run {
                        _mSurveysFlow.value = SurveyEvent.Failure("Unexpected error")
                    }

                }
            }
        }
    }

}