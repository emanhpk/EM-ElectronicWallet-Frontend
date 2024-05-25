package com.example.em_electronicwallet_frontend.ui_components.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.em_electronicwallet_frontend.databinding.ActivityDepositMoneyBinding

class DepositMoneyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDepositMoneyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDepositMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        init()
    }

    private fun init() {
        binding.imageBack.setOnClickListener {
            startActivity(Intent(this@DepositMoneyActivity, MainActivity::class.java))
        }

        binding.bottomSendSavingOnline.setOnClickListener {
            startActivity(Intent(this@DepositMoneyActivity, SendSavingsActivity::class.java))
        }

        binding.bottomWithdrawSavingOnline.setOnClickListener {
            startActivity(Intent(this@DepositMoneyActivity, WithdrawSavingsActivity::class.java))
        }

        binding.bottomOpenDepositOnline.setOnClickListener {
            startActivity(Intent(this@DepositMoneyActivity, OpenDepositActivity::class.java))
        }

        binding.bottomSendDepositOnline.setOnClickListener {
            startActivity(Intent(this@DepositMoneyActivity, SendDepositActivity::class.java))
        }

        binding.bottomWithdrawDepositOnline.setOnClickListener {
            startActivity(Intent(this@DepositMoneyActivity, WithdrawDepositActivity::class.java))
        }
    }
}