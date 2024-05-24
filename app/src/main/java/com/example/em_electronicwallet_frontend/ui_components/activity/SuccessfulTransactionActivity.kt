package com.example.em_electronicwallet_frontend.ui_components.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.em_electronicwallet_frontend.R
import com.example.em_electronicwallet_frontend.databinding.ActivitySuccessfulTransactionBinding
import com.example.em_electronicwallet_frontend.ui_components.fragment.HomeFragment

class SuccessfulTransactionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuccessfulTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessfulTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        init()
    }

    private fun init() {
        binding.bottomNewTransaction.setOnClickListener {
            startActivity(Intent(this@SuccessfulTransactionActivity, WireTransferActivity::class.java))
        }

        binding.bottomBackHome.setOnClickListener {
            startActivity(Intent(this@SuccessfulTransactionActivity, MainActivity::class.java))
        }
    }
}