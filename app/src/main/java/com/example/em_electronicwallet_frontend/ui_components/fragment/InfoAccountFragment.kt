package com.example.em_electronicwallet_frontend.ui_components.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.em_electronicwallet_frontend.databinding.FragmentInfoAccountBinding
import com.example.em_electronicwallet_frontend.ui_components.activity.WireTransferActivity

class InfoAccountFragment : Fragment() {
    private var _binding: FragmentInfoAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoAccountBinding.inflate(inflater, container, false)

        init()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.textClose.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.bottomTransferMoney.setOnClickListener {
            val intent = Intent(requireContext(), WireTransferActivity::class.java)
            startActivity(intent)
        }
    }
}