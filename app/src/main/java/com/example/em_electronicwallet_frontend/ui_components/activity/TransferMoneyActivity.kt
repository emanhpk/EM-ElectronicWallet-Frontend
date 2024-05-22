package com.example.em_electronicwallet_frontend.ui_components.activity

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.em_electronicwallet_frontend.R
import com.example.em_electronicwallet_frontend.databinding.ActivityTransferMoneyBinding
import com.example.em_electronicwallet_frontend.ui_components.fragment.BeneficiaryPhonebookFragment
import com.example.em_electronicwallet_frontend.ui_components.fragment.BeneficiaryRecentlyFragment

class TransferMoneyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransferMoneyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        init()
        initTransferFast()
    }

    private fun init() {
        binding.imageBack.setOnClickListener { finish() }
    }

    private fun initTransferFast() {
        replaceFragment(BeneficiaryPhonebookFragment())
        updateUIBottom(0)

        binding.bottomPhonebook.setOnClickListener {
            replaceFragment(BeneficiaryPhonebookFragment())
            updateUIBottom(0)
        }

        binding.bottomRecently.setOnClickListener {
            replaceFragment(BeneficiaryRecentlyFragment())
            updateUIBottom(1)
        }
    }

    private fun updateUIBottom(position: Int) {
        val colors = intArrayOf(
            ContextCompat.getColor(this, R.color.grey),
            ContextCompat.getColor(this, R.color.black)
        )

        binding.textPhonebook.setTextColor(colors[if (position == 0) 1 else 0])
        binding.textRecently.setTextColor(colors[if (position == 1) 1 else 0])

        binding.viewPhonebook.visibility = if (position == 0) View.VISIBLE else View.INVISIBLE
        binding.viewRecently.visibility = if (position == 1) View.VISIBLE else View.INVISIBLE
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.layoutTransferFast, fragment)
        fragmentTransaction.commit()
    }
}