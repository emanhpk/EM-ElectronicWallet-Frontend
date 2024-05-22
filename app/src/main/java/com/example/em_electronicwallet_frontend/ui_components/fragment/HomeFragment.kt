package com.example.em_electronicwallet_frontend.ui_components.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.em_electronicwallet_frontend.R
import com.example.em_electronicwallet_frontend.databinding.FragmentHomeBinding
import com.example.em_electronicwallet_frontend.ui_components.activity.TransferMoneyActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentHomeBinding? = null
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

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
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun init() {
        binding.bottomInfoAccount.setOnClickListener {
            replaceFragment(InfoAccountFragment())
        }

        binding.bottomTransferMoney.setOnClickListener {navigateToTransferMoney()
        }

        binding.bottomTransferMoney2.setOnClickListener {navigateToTransferMoney()
        }
    }

    private fun navigateToTransferMoney() {
        val intent = Intent(requireContext(), TransferMoneyActivity::class.java)
        startActivity(intent)
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.layoutInfoAccount, fragment)
            .addToBackStack(null)
            .commit()
    }
}