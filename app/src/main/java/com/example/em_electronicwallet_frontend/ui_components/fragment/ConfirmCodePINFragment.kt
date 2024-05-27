package com.example.em_electronicwallet_frontend.ui_components.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.em_electronicwallet_frontend.R
import com.example.em_electronicwallet_frontend.databinding.FragmentConfirmCodePINBinding
import com.example.em_electronicwallet_frontend.ui_components.activity.SuccessfulTransactionActivity

class ConfirmCodePINFragment : Fragment() {
    private var _binding: FragmentConfirmCodePINBinding? = null
    private val binding get() = _binding!!

    private lateinit var firstPinInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmCodePINBinding.inflate(inflater, container, false)

        setupInputCodePIN()

        firstPinInput = binding.pinInputLayout.getChildAt(0) as EditText

        firstPinInput.requestFocus()
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.showSoftInput(firstPinInput, InputMethodManager.SHOW_IMPLICIT)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupInputCodePIN() {
        val layoutParams = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1f
        ).apply {
            setMargins(8, 0, 8, 0)
        }
        val inputFilter = InputFilter.LengthFilter(1)
        val backgroundDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.pin_input_box)
        val inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD

        var currentFocus = 0

        repeat(6) { i ->
            EditText(requireContext()).apply {
                this.layoutParams = layoutParams
                this.inputType = inputType
                this.filters = arrayOf(inputFilter)
                this.gravity = Gravity.CENTER
                this.background = backgroundDrawable

                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        currentFocus = i
                        showKeyboard(this)
                    }
                }

                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {

                    }

                    override fun afterTextChanged(s: Editable?) {
                        if (s?.length == 1) {
                            clearFocus()
                            if (currentFocus < 5) {
                                binding.pinInputLayout.getChildAt(currentFocus + 1).requestFocus()
                            } else {
                                val inputPIN = getEnteredPIN()
                                checkPINAndNavigate(inputPIN)
                            }
                        } else if (s.isNullOrEmpty()) {
                            if (currentFocus > 0) {
                                binding.pinInputLayout.getChildAt(currentFocus - 1).requestFocus()
                            }
                        }

                        setSelection(text?.length ?: 0)
                    }
                })

                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        val inputPIN = getEnteredPIN()
                        checkPINAndNavigate(inputPIN)
                        true
                    } else {
                        false
                    }
                }

            }.also { binding.pinInputLayout.addView(it) }
        }
    }

    private fun getEnteredPIN(): String {
        val pinStringBuilder = StringBuilder()
        for (i in 0 until binding.pinInputLayout.childCount) {
            val child = binding.pinInputLayout.getChildAt(i)
            if (child is EditText) {
                pinStringBuilder.append(child.text.toString())
            }
        }
        return pinStringBuilder.toString()
    }

    private fun checkPINAndNavigate(pin: String) {
        if (isValidPIN(pin)) {
            val intent = Intent(requireContext(), SuccessfulTransactionActivity::class.java)
            startActivity(intent)
        } else {
            "Bạn đã nhập sai mã PIN!".showToast()

            for (i in 0 until binding.pinInputLayout.childCount) {
                val child = binding.pinInputLayout.getChildAt(i)
                if (child is EditText) {
                    child.text.clear()
                }
            }

            val firstPinInput = binding.pinInputLayout.getChildAt(0) as EditText
            firstPinInput.requestFocus()
        }
    }

    private fun isValidPIN(pin: String): Boolean {
        return pin == "123456"
    }

    private fun showKeyboard(view: View) {
        val imm = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

    private fun String.showToast() {
        Toast.makeText(requireContext(), this, Toast.LENGTH_SHORT).show()
    }
}