package com.android.sampleTest.feature.survey

import android.os.Bundle
import com.android.sampleTest.R
import com.android.sampleTest.base.BaseActivity
import com.android.sampleTest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<SurveyViewModel, ActivityMainBinding>(SurveyViewModel::class.java) {
    override fun setupViews() {
    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.backBtn.setOnClickListener { this.finish() }
    }
}