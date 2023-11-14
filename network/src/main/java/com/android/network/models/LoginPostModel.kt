package com.android.network.models

import com.google.gson.annotations.SerializedName

data class LoginPostModel(
    @SerializedName("grant_type")
    val grantType: String = "password",
    val email: String     = "raza@example.com",
    val password: String  = "12345678",
    @SerializedName("client_id")
    val clientId: String = "6GbE8dhoz519l2N_F99StqoOs6Tcmm1rXgda4q__rIw",
    @SerializedName("client_secret")
    val clientSecret: String = "_ayfIm7BeUAhx2W1OUqi20fwO3uNxfo1QstyKlFCgHw",
)