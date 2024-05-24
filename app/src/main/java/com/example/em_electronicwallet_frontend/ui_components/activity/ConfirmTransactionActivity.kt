package com.example.em_electronicwallet_frontend.ui_components.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.em_electronicwallet_frontend.R
import com.example.em_electronicwallet_frontend.databinding.ActivityConfirmTransactionBinding
import com.example.em_electronicwallet_frontend.ui_components.fragment.ConfirmCodePINFragment

class ConfirmTransactionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        init()
    }

    private fun init() {
        binding.imageBack.setOnClickListener { finish() }

        binding.bottomConfirm.setOnClickListener {
            replaceFragment(ConfirmCodePINFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.layoutCodePin, fragment)
        fragmentTransaction.commit()
    }
}