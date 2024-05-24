package com.example.em_electronicwallet_frontend.ui_components.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.em_electronicwallet_frontend.R
import com.example.em_electronicwallet_frontend.databinding.ActivitySendDepositOnlineBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class SendDepositOnlineActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySendDepositOnlineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendDepositOnlineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        init()
        setupEditText()
    }

    private fun init() {
        binding.bottomSelectTerm.setOnClickListener {
            setupSelectTerm()
        }

        binding.bottomSelectMaturityMethod.setOnClickListener {
            setupSelectMaturityMethod()
        }

        binding.bottomNext.setOnClickListener {
            startActivity(Intent(this@SendDepositOnlineActivity, ConfirmTransactionActivity::class.java))
        }
    }

    private fun setupSelectTerm() {
        val items = arrayOf(
            "Kỳ hạn: 1 Tuần - 0.3%",
            "Kỳ hạn: 2 Tuần - 0.3%",
            "Kỳ hạn: 3 Tuần - 0.3%",
            "Kỳ hạn: 1 Tháng - 2%",
            "Kỳ hạn: 2 Tháng - 2%",
            "Kỳ hạn: 3 Tháng - 2.3%",
            "Kỳ hạn: 4 Tháng - 2.3%",
            "Kỳ hạn: 5 Tháng - 2.3%",
            "Kỳ hạn: 6 Tháng - 3.3%",
            "Kỳ hạn: 7 Tháng - 3.3%",
            "Kỳ hạn: 8 Tháng - 3.3%",
            "Kỳ hạn: 9 Tháng - 3.3%",
            "Kỳ hạn: 10 Tháng - 3.3%",
            "Kỳ hạn: 11 Tháng - 3.3%",
            "Kỳ hạn: 12 Tháng - 4.7%",
            "Kỳ hạn: 13 Tháng - 4.7%",
            "Kỳ hạn: 15 Tháng - 4.7%",
            "Kỳ hạn: 18 Tháng - 4.7%",
            "Kỳ hạn: 24 Tháng - 4.8%",
            "Kỳ hạn: 36 Tháng - 4.8%"
        )
        showItemSelectionTermDialog(items)
    }

    private fun showItemSelectionTermDialog(items: Array<String>) {
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

    private fun setupSelectMaturityMethod() {
        val items = arrayOf(
            "Tái tục gốc và lãi",
            "Nhận lãi, tái tục gốc"
        )
        showItemSelectionMaturityMethodDialog(items)
    }

    private fun showItemSelectionMaturityMethodDialog(items: Array<String>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Chọn phương thức đáo hạn")
        builder.setSingleChoiceItems(items, -1) { dialog, which ->
            val selectedItem = items[which]
            binding.textSelectMaturityMethod.text = selectedItem
            binding.textSelectMaturityMethod.setTextColor(ContextCompat.getColor(this, R.color.black))
            dialog.dismiss()
        }
        builder.show()
    }

    private fun setupEditText() {
        val symbols = DecimalFormatSymbols(Locale("vi", "VN"))
        symbols.groupingSeparator = '.'
        val decimalFormat = DecimalFormat("#,###", symbols)

        binding.inputMoneySend.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.inputMoneySend.removeTextChangedListener(this)

                if (!s.isNullOrEmpty()) {
                    try {
                        val parsedNumber = s.toString().replace(".", "").toLong()
                        val formatted = decimalFormat.format(parsedNumber)
                        binding.inputMoneySend.setText(formatted)
                        binding.inputMoneySend.setSelection(formatted.length)
                    } catch (_: NumberFormatException) {

                    }
                }

                binding.inputMoneySend.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}