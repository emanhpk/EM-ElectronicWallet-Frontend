package com.example.em_electronicwallet_frontend.ui_components.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.em_electronicwallet_frontend.databinding.ActivityWithdrawDepositBinding

class WithdrawDepositActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWithdrawDepositBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithdrawDepositBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        init()
    }

    private fun init() {
        binding.imageBack.setOnClickListener { finish() }
    }
}