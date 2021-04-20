package com.fulora.app

import android.app.Application
import androidx.room.Room
import com.fulora.app.source.local.FuloraDatabase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class FuloraApplication: Application() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()
        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics
    }
}