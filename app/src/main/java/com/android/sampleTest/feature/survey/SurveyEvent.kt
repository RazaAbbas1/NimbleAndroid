package com.android.sampleTest.feature.survey

import com.android.network.models.SurveyData
import com.android.network.models.SurveyDataAttributes

sealed class SurveyEvent{
    class Success(val result: List<SurveyData>):SurveyEvent()
    class Failure(val errorText:String):SurveyEvent()
    data object Loading:SurveyEvent()
    data object Empty:SurveyEvent()
}