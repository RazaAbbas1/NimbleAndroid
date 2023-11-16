package com.android.sampleTest.feature.forgot

import com.android.network.models.MetaObj

sealed class ForgotEvent{
    class Success(val result: MetaObj):ForgotEvent()
    class Failure(val errorText:String):ForgotEvent()
    data object Loading:ForgotEvent()
    data object Empty:ForgotEvent()
}