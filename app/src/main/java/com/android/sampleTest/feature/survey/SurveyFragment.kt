package com.android.sampleTest.feature.survey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.network.models.SurveyData
import com.android.sampleTest.databinding.FragmentSurveyBinding
import com.squareup.picasso.Picasso


class SurveyFragment(private val mData: SurveyData): Fragment(){
    private lateinit var binding: FragmentSurveyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSurveyBinding.inflate(inflater, container, false)
        initData()
        return binding.root
    }

    private fun initData() {
        binding.tvTitle.text = mData.attributes.title
        binding.tvPara.text = mData.attributes.description

        binding.surveyImg.let { Picasso.get().load(mData.attributes.coverImageUrl).into(it) }
    }


    companion object{
        fun newInstance(surveyData: SurveyData) = SurveyFragment(surveyData)
    }
}