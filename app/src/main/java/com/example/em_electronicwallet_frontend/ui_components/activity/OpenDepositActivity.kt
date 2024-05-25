package com.example.em_electronicwallet_frontend.ui_components.activity

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.em_electronicwallet_frontend.R
import com.example.em_electronicwallet_frontend.databinding.ActivityOpenDepositBinding

class OpenDepositActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOpenDepositBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenDepositBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        init()
    }

    private fun init() {
        binding.imageBack.setOnClickListener { finish() }

        binding.bottomSelectDeposit.setOnClickListener {
            setupSelectTypesDeposit()
        }

        binding.bottomSelectTerm.setOnClickListener {
            when (binding.textSelectDeposit.text.toString()) {
                "Cumulative deposit account - TGTL online" -> setupSelectTermCumulative()
                "Dream deposit - Tích lũy mơ ước" -> setupSelectTermDream()
                "Home deposit - TGTL Mua nhà An Phú Gia" -> setupSelectTermHome()
                else -> {
                    "Bạn chưa chọn loại sản phẩm tích lũy".showToast()
                }
            }
        }
    }

    private fun setupSelectTypesDeposit() {
        val items = arrayOf(
            "Cumulative deposit account - TGTL online",
            "Dream deposit - Tích lũy mơ ước",
            "Home deposit - TGTL Mua nhà An Phú Gia",
        )
        showItemSelectDepositDialog(items)
    }

    private fun showItemSelectDepositDialog(items: Array<String>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Loại sản phẩm tích lũy")
        builder.setSingleChoiceItems(items, -1) { dialog, which ->
            val selectedItem = items[which]
            binding.textSelectDeposit.text = selectedItem
            binding.textSelectDeposit.setTextColor(ContextCompat.getColor(this, R.color.black))
            dialog.dismiss()

            binding.textSelectTerm.text = getString(R.string.select_term)
            binding.textSelectTerm.setTextColor(ContextCompat.getColor(this, R.color.grey))
        }
        builder.show()
    }

    private fun setupSelectTermCumulative() {
        val items = arrayOf(
            "6 tháng",
            "12 tháng",
            "24 tháng",
            "36 tháng",
            "48 tháng",
            "60 tháng",
        )
        showItemSelectTermDialog(items)
    }

    private fun setupSelectTermDream() {
        val items = arrayOf(
            "6 tháng",
            "12 tháng",
            "24 tháng"
        )
        showItemSelectTermDialog(items)
    }

    private fun setupSelectTermHome() {
        val items = arrayOf(
            "12 tháng",
            "24 tháng",
        )
        showItemSelectTermDialog(items)
    }

    private fun showItemSelectTermDialog(items: Array<String>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Chọn kỳ hạn")
        builder.setSingleChoiceItems(items, -1) { dialog, which ->
            val selectedItem = items[which]
            binding.textSelectTerm.text = selectedItem
            binding.textSelectTerm.setTextColor(ContextCompat.getColor(this, R.color.black))
            dialog.dismiss()
        }
        builder.show()
    }

    private fun String.showToast() {
        Toast.makeText(applicationContext, this, Toast.LENGTH_SHORT).show()
    }
}