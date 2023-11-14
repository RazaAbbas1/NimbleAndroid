package com.android.network.models

import com.google.gson.annotations.SerializedName

data class SurveysResponseModel(
    val data: List<SurveyData>?,
    val meta: Meta,
)

data class SurveyData(
    val id: String,
    val type: String,
    val attributes: SurveyDataAttributes,
)

data class SurveyDataAttributes(
    val title: String,
    val description: String,
    @SerializedName("thank_email_above_threshold")
    val thankEmailAboveThreshold: String?,
    @SerializedName("thank_email_below_threshold")
    val thankEmailBelowThreshold: String?,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("cover_image_url")
    val coverImageUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("active_at")
    val activeAt: String,
    @SerializedName("inactive_at")
    val inactiveAt: Any?,
    @SerializedName("survey_type")
    val surveyType: String,
)

data class Meta(
    val page: Long,
    val pages: Long,
    @SerializedName("page_size")
    val pageSize: Long,
    val records: Long,
)
