package com.android.sampleTest.feature.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.android.network.models.Attributes
import com.android.sampleTest.R
import com.android.sampleTest.base.BaseActivity
import com.android.sampleTest.databinding.ActivityLoginBinding
import com.android.sampleTest.feature.survey.SurveyEvent
import com.android.sampleTest.session.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>(LoginViewModel::class.java) {


    private lateinit var sessionManager: SessionManager



    override fun setupViews() {

    }

    override fun getLayoutResId(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

    }

    private fun initData() {
        sessionManager = SessionManager(this)

        doLoginOrFetchToken()
    }

    private fun doLoginOrFetchToken() {
        when (sessionManager.loginInfo) {
            null -> viewModel.loginApiCall()
            else -> viewModel.refreshTokenApiCall(sessionManager.loginInfo!!.refreshToken)
        }

        lifecycleScope.launch {
            viewModel.conversion.collect { value ->
                when(value){
                    is LoginEvent.Success ->{
                        binding.progressBar.visibility = View.GONE
                        handleLoginResponse(value.result)
                    }
                    is LoginEvent.Failure -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    is LoginEvent.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun handleLoginResponse(mData: Attributes){
        sessionManager.loginInfo = mData


        fetchSurveysList()
    }

    private fun fetchSurveysList() {

        viewModel.fetchSurveys()

        lifecycleScope.launch {
            viewModel.surveyDataFlow.collect { value ->
                when(value){
                    is SurveyEvent.Success -> {
                        binding.progressBar.visibility = View.GONE
                        Log.e("YAYA", "Yaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaay")
                    }
                    is SurveyEvent.Failure -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    is SurveyEvent.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    else -> Unit
                }
            }
        }
    }
}