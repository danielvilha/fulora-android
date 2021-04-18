package com.fulora.app.sign_in

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fulora.app.R
import com.fulora.app.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        Spannable
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, SignInActivity::class.java)
        }
    }
}
