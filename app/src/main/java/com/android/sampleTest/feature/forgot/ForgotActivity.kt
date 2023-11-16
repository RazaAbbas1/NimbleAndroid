package com.android.sampleTest.feature.forgot

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android.network.models.MetaObj
import com.android.sampleTest.R
import com.android.sampleTest.base.BaseActivity
import com.android.sampleTest.databinding.ActivityForgotBinding
import com.android.sampleTest.session.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ForgotActivity : BaseActivity<ForgotViewModel, ActivityForgotBinding>(ForgotViewModel::class.java) {

    private lateinit var sessionManager: SessionManager

    override fun setupViews() {

    }

    override fun getLayoutResId(): Int = R.layout.activity_forgot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

    }

    private fun initData() {
        sessionManager = SessionManager(this)

        binding.email = viewModel.email

        binding.backBtn.setOnClickListener { this.finish() }
        binding.resetBtn.setOnClickListener { resetAPI() }
    }


    private fun resetAPI() {

        viewModel.resetApiCall()

        lifecycleScope.launch {
            viewModel.forgotFLow.collect { value ->
                when(value){
                    is ForgotEvent.Success ->{
                        binding.progressBar.visibility = View.GONE
                        handleForgotResponse(value.result)
                    }
                    is ForgotEvent.Failure -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@ForgotActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                    is ForgotEvent.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun handleForgotResponse(result: MetaObj) {
        showAlert(result.message, showCancel = false) {}
    }


}