package com.fulora.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fulora.offline.OfflineActivity
import com.fulora.online.OnlineActivity
import com.fulora.preference.getUserUid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MainActivity : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityScope.launch {
            delay(1000L)

            if (getUserUid(this@MainActivity).isNullOrBlank())
                startActivity(Intent(this@MainActivity, OfflineActivity::class.java))
            else
                startActivity(Intent(this@MainActivity, OnlineActivity::class.java))
        }
    }
}