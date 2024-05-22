package com.example.em_electronicwallet_frontend.ui_components.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.em_electronicwallet_frontend.databinding.FragmentInfoAccountBinding
import com.example.em_electronicwallet_frontend.ui_components.activity.WireTransferActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class InfoAccountFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentInfoAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoAccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
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