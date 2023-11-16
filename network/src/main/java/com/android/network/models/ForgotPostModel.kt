package com.android.network.models

import com.android.network.Constants
import com.google.gson.annotations.SerializedName

data class ForgotPostModel(
    val user: User,
    @SerializedName("client_id")
    val clientId: String = Constants.CLIENT_ID,
    @SerializedName("client_secret")
    val clientSecret: String = Constants.CLIENT_SECRET_KEY
)

data class User(
    val email: String,
)