package com.android.sampleTest.feature.survey

import com.android.network.models.SurveyData
import com.android.network.models.SurveyDataAttributes
import com.android.network.models.SurveysResponseModel

sealed class SurveyEvent{
    class Success(val responseModel: SurveysResponseModel):SurveyEvent()
    class Failure(val errorText:String):SurveyEvent()
    data object Loading:SurveyEvent()
    data object Empty:SurveyEvent()
}