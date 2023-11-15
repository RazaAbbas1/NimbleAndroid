package com.android.sampleTest.feature.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.android.sampleTest.R
import com.android.sampleTest.base.BaseActivity
import com.android.sampleTest.databinding.ActivitySplashBinding
import com.android.sampleTest.feature.login.LoginActivity
import com.android.sampleTest.feature.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity :
    BaseActivity<LoginViewModel, ActivitySplashBinding>(LoginViewModel::class.java) {

    override fun setupViews() {
    }


    override fun getLayoutResId(): Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }


    private fun init() {
        moveToNextActivity()
    }

    private fun moveToNextActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(applicationContext?.let { createLoginActivity(it) })
            this.finish()
        }, 2000)

    }
    private fun createLoginActivity(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }
}
