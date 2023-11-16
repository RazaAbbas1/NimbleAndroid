package com.android.sampleTest.feature.survey

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.android.network.models.SurveysResponseModel
import com.android.sampleTest.R
import com.android.sampleTest.base.BaseActivity
import com.android.sampleTest.databinding.ActivitySurveysBinding
import com.android.sampleTest.feature.login.LoginActivity
import com.android.sampleTest.session.SessionManager
import com.android.sampleTest.utils.TimeUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SurveysActivity : BaseActivity<SurveyViewModel, ActivitySurveysBinding>(SurveyViewModel::class.java) {

    private lateinit var sessionManager: SessionManager
    private lateinit var myAdapter: PagerAdapter

    override fun setupViews() {

    }

    override fun getLayoutResId(): Int = R.layout.activity_surveys

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
    }

    private fun loadData(){
        binding.shimmerViewContainer.startShimmerAnimation()
        fetchSurveysList()
    }

    private fun fetchSurveysList() {

        viewModel.fetchSurveys()

        lifecycleScope.launch {
            viewModel.surveyDataFlow.collect { value ->
                when(value){
                    is SurveyEvent.Success -> {
                        initData(value.responseModel)
                    }
                    is SurveyEvent.Failure -> {
                        binding.shimmerViewContainer.visibility = View.GONE
                    }
                    is SurveyEvent.Loading -> {}
                    else -> Unit
                }
            }
        }
    }

    private fun initData(model: SurveysResponseModel) {

        binding.tvDate.text = TimeUtils.getCurrentDate()
        myAdapter = PagerAdapter(supportFragmentManager, lifecycle)

        // add Fragments in your ViewPagerFragmentAdapter class

        model.let {
            it.data?.let { surveyData ->
                repeat(surveyData.size) { index ->
                    myAdapter.addFragment(SurveyFragment.newInstance(surveyData[index]))
                }
            }
        }


        // set Orientation in your ViewPager2
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.viewPager.adapter = myAdapter
        binding.dotsIndicator.attachTo(binding.viewPager)
        sessionManager = SessionManager(this)

        binding.btnDetails.setOnClickListener { moveToNextActivity() }
        binding.cardView.setOnClickListener { openDialogForLogout() }

        binding.shimmerViewContainer.stopShimmerAnimation()
        binding.shimmerViewContainer.visibility = View.GONE
        binding.cardView.visibility = View.VISIBLE

    }

    private fun openDialogForLogout() {
        showAlert("Logout ?", "Yes") {
            sessionManager.clear()
            startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }
    }

    private fun moveToNextActivity() {
        startActivity(applicationContext?.let { createDetailsActivity(it) })
    }
    private fun createDetailsActivity(context: Context): Intent {
        return Intent(context, MainActivity::class.java)
    }
}