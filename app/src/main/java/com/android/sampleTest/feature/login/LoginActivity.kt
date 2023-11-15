package com.android.sampleTest.feature.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android.network.models.Attributes
import com.android.sampleTest.R
import com.android.sampleTest.base.BaseActivity
import com.android.sampleTest.databinding.ActivityLoginBinding
import com.android.sampleTest.feature.survey.SurveysActivity
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

        binding.userEmail = viewModel.userEmail
        binding.password = viewModel.password

        checkTokenAndDoAutoLogin()

        binding.signInBtn.setOnClickListener { doLogin() }
    }

    private fun checkTokenAndDoAutoLogin() {
        when (sessionManager.loginInfo) {
            null -> binding.progressBar.visibility = View.GONE
            else -> viewModel.refreshTokenApiCall(sessionManager.loginInfo!!.refreshToken)
        }

        lifecycleScope.launch {
            viewModel.loginFLow.collect { value ->
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

    private fun doLogin() {

        viewModel.loginApiCall()

        lifecycleScope.launch {
            viewModel.loginFLow.collect { value ->
                when(value){
                    is LoginEvent.Success ->{
                        binding.progressBar.visibility = View.GONE
                        handleLoginResponse(value.result)
                    }
                    is LoginEvent.Failure -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@LoginActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
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

        openSurveysScreen()

    }

    private fun openSurveysScreen() {
        startActivity(Intent(this, SurveysActivity::class.java))
        this.finish()
    }
}