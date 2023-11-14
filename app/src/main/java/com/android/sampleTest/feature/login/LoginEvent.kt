package com.android.sampleTest.feature.login

import com.android.network.models.Attributes

sealed class LoginEvent{
    class Success(val result: Attributes):LoginEvent()
    class Failure(val errorText:String):LoginEvent()
    data object Loading:LoginEvent()
    data object Empty:LoginEvent()
}