package com.example.em_electronicwallet_frontend.ui_components.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.em_electronicwallet_frontend.databinding.ActivityTransactionDetailsBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class TransactionDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        init()
        setupEditText()
    }

    private fun init() {
        binding.imageBack.setOnClickListener { finish() }
    }

    private fun setupEditText() {
        val symbols = DecimalFormatSymbols(Locale("vi", "VN"))
        symbols.groupingSeparator = '.'
        val decimalFormat = DecimalFormat("#,###", symbols)

        binding.inputNumberMoney.addTextChangedListener(object : TextWatcher {
            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(s: Editable?) {
                binding.inputNumberMoney.removeTextChangedListener(this)

                if (!s.isNullOrEmpty()) {
                    try {
                        val parsedNumber = s.toString().replace(".", "").toLong()
                        val formatted = decimalFormat.format(parsedNumber)
                        binding.inputNumberMoney.setText(formatted)
                        binding.inputNumberMoney.setSelection(formatted.length)
                        val numberInWords = convertNumberToWords(parsedNumber)
                        binding.textNumberMoneyInText.text = numberInWords.replaceFirstChar { it.uppercaseChar() } + " đồng"
                    } catch (e: NumberFormatException) {
                        binding.textNumberMoneyInText.text = ""
                    }
                } else {
                    binding.textNumberMoneyInText.text = ""
                }

                binding.inputNumberMoney.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun convertNumberToWords(number: Long): String {
        if (number == 0L) return "không"

        val units = arrayOf("", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín")
        val teens = arrayOf("mười", "mười một", "mười hai", "mười ba", "mười bốn", "mười lăm", "mười sáu", "mười bảy", "mười tám", "mười chín")
        val hundreds = arrayOf("", "một trăm", "hai trăm", "ba trăm", "bốn trăm", "năm trăm", "sáu trăm", "bảy trăm", "tám trăm", "chín trăm")
        val thousandsGroups = arrayOf("", "nghìn", "triệu", "tỷ")

        fun convertThreeDigits(num: Int): String {
            val hundred = num / 100
            val ten = (num % 100) / 10
            val unit = num % 10
            val result = StringBuilder()

            if (hundred > 0) result.append(hundreds[hundred]).append(" ")
            if (ten >= 2) result.append(units[ten]).append(" mươi ")
            else if (ten == 1) result.append(teens[unit]).append(" ")
            if (unit > 0 && ten != 1) result.append(units[unit])
            return result.toString().trim()
        }

        val parts = mutableListOf<String>()
        var tempNumber = number
        var count = 0

        while (tempNumber > 0) {
            val threeDigits = (tempNumber % 1000).toInt()
            parts.add(0, convertThreeDigits(threeDigits) + " " + thousandsGroups[count])
            tempNumber /= 1000
            count++
        }

        return parts.joinToString(" ").trim()
    }
}