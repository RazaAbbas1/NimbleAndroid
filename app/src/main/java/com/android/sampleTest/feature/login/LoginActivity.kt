package com.android.sampleTest.feature.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.android.sampleTest.R
import com.android.sampleTest.base.BaseActivity
import com.android.sampleTest.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>(LoginViewModel::class.java) {
    override fun setupViews() {

    }

    override fun getLayoutResId(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel.loginApiCall()

        lifecycleScope.launch {
            viewModel.conversion.collect { value ->
                when(value){
                    is LoginEvent.Success ->{
                        binding.progressBar.visibility = View.GONE
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
}