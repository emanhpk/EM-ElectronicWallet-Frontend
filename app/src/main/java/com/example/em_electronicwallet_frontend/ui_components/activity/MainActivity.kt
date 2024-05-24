package com.example.em_electronicwallet_frontend.ui_components.activity

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.em_electronicwallet_frontend.R
import com.example.em_electronicwallet_frontend.databinding.ActivityMainBinding
import com.example.em_electronicwallet_frontend.ui_components.fragment.HomeFragment
import com.example.em_electronicwallet_frontend.ui_components.fragment.NotificationFragment
import com.example.em_electronicwallet_frontend.ui_components.fragment.SettingFragment
import com.example.em_electronicwallet_frontend.ui_components.fragment.ShoppingFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        initFragment()
    }

    private fun initFragment() {
        replaceFragment(HomeFragment())
        updateUIBottom(0)

        binding.bottomHome.setOnClickListener {
            replaceFragment(HomeFragment())
            updateUIBottom(0)
        }

        binding.bottomShopping.setOnClickListener {
            replaceFragment(ShoppingFragment())
            updateUIBottom(1)
        }

        binding.bottomNotification.setOnClickListener {
            replaceFragment(NotificationFragment())
            updateUIBottom(2)
        }

        binding.bottomSetting.setOnClickListener {
            replaceFragment(SettingFragment())
            updateUIBottom(3)
        }
    }

    private fun updateUIBottom(position: Int) {
        val colors = intArrayOf(
            ContextCompat.getColor(this, R.color.grey),
            ContextCompat.getColor(this, R.color.blue)
        )

        binding.imageHome.setColorFilter(colors[if (position == 0) 1 else 0])
        binding.imageShopping.setColorFilter(colors[if (position == 1) 1 else 0])
        binding.imageNotification.setColorFilter(colors[if (position == 2) 1 else 0])
        binding.imageSetting.setColorFilter(colors[if (position == 3) 1 else 0])

        binding.textHome.setTextColor(colors[if (position == 0) 1 else 0])
        binding.textShopping.setTextColor(colors[if (position == 1) 1 else 0])
        binding.textNotification.setTextColor(colors[if (position == 2) 1 else 0])
        binding.textSetting.setTextColor(colors[if (position == 3) 1 else 0])

        binding.viewHome.visibility = if (position == 0) View.VISIBLE else View.INVISIBLE
        binding.viewShopping.visibility = if (position == 1) View.VISIBLE else View.INVISIBLE
        binding.viewNotification.visibility = if (position == 2) View.VISIBLE else View.INVISIBLE
        binding.viewSetting.visibility = if (position == 3) View.VISIBLE else View.INVISIBLE
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayoutMain, fragment)
        fragmentTransaction.commit()
    }
}