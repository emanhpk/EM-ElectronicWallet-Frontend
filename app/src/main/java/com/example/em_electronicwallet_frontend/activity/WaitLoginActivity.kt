package com.example.em_electronicwallet_frontend.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.em_electronicwallet_frontend.databinding.ActivityWaitLoginBinding

class WaitLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWaitLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWaitLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        init()
    }

    private fun init() {
        binding.bottomLogin.setOnClickListener {
            startActivity(Intent(this@WaitLoginActivity, MainActivity::class.java))
        }
    }
}