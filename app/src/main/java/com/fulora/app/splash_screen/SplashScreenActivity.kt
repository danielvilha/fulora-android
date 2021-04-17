package com.fulora.app.splash_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fulora.app.R
import com.fulora.app.databinding.ActivitySplashScreenBinding
import com.fulora.app.onboarding.OnboardingActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    val activityScope = CoroutineScope(Dispatchers.Main)
    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        activityScope.launch {
            delay(1000)
            startActivity(OnboardingActivity.createIntent(this@SplashScreenActivity))
            finish()
        }
    }
}