package com.android.network.models

import com.android.network.Constants
import com.android.network.Constants.CLIENT_ID
import com.android.network.Constants.CLIENT_SECRET_KEY
import com.android.network.Constants.GRANT_TYPE_PASSWORD
import com.google.gson.annotations.SerializedName

data class LoginPostModel(
    @SerializedName("grant_type")
    val grantType: String = GRANT_TYPE_PASSWORD,
    val email: String     = "",
    val password: String  = "",
    @SerializedName("client_id")
    val clientId: String = CLIENT_ID,
    @SerializedName("client_secret")
    val clientSecret: String = CLIENT_SECRET_KEY
)