package com.fulora.app.splash_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    val activityScope = CoroutineScope(Dispatchers.Main)
//    lateinit var binding :

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityScope.launch {

        }
    }
}