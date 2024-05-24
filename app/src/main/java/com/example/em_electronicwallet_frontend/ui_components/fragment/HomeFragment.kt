package com.example.em_electronicwallet_frontend.ui_components.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.em_electronicwallet_frontend.R
import com.example.em_electronicwallet_frontend.databinding.FragmentHomeBinding
import com.example.em_electronicwallet_frontend.ui_components.activity.DepositMoneyActivity
import com.example.em_electronicwallet_frontend.ui_components.activity.WireTransferActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        init()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.bottomInfoAccount.setOnClickListener {
            replaceFragment(InfoAccountFragment())
        }

        binding.bottomTransferMoney.setOnClickListener {
            navigateToTransferMoney()
        }

        binding.bottomTransferMoney2.setOnClickListener {
            navigateToTransferMoney()
        }

        binding.bottomSaveMoney.setOnClickListener {
            val intent = Intent(requireContext(), DepositMoneyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToTransferMoney() {
        val intent = Intent(requireContext(), WireTransferActivity::class.java)
        startActivity(intent)
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.layoutInfoAccount, fragment)
            .addToBackStack(null)
            .commit()
    }
}