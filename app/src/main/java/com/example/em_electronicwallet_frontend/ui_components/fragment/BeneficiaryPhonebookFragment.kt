package com.example.em_electronicwallet_frontend.ui_components.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.em_electronicwallet_frontend.adapter.BeneficiaryPhonebookAdapter
import com.example.em_electronicwallet_frontend.databinding.FragmentBeneficiaryPhonebookBinding
import com.example.em_electronicwallet_frontend.model.BeneficiaryPhonebookModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BeneficiaryPhonebookFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentBeneficiaryPhonebookBinding? = null

    private val binding get() = _binding!!

    private val beneficiaryPhonebookAdapter by lazy { BeneficiaryPhonebookAdapter() }

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
        _binding = FragmentBeneficiaryPhonebookBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BeneficiaryPhonebookFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setupRecyclerView() {
        val list = loadData()
        beneficiaryPhonebookAdapter.differ.submitList(list)
        binding.beneficiaryPhonebookView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = beneficiaryPhonebookAdapter
        }
    }

    private fun loadData() : MutableList<BeneficiaryPhonebookModel> {
        val beneficiary: MutableList<BeneficiaryPhonebookModel> = mutableListOf()
        beneficiary.add(BeneficiaryPhonebookModel("Cristiano Ronaldo", "0387462833"))
        beneficiary.add(BeneficiaryPhonebookModel("Lionel Messi", "0388273221"))
        beneficiary.add(BeneficiaryPhonebookModel("Kylian Mbappé", "0380192783"))
        beneficiary.add(BeneficiaryPhonebookModel("Jude Bellingham", "0380102232"))
        beneficiary.add(BeneficiaryPhonebookModel("Jamal Musiala", "0387918223"))
        beneficiary.add(BeneficiaryPhonebookModel("Cole Palmer", "0387482883"))
        beneficiary.add(BeneficiaryPhonebookModel("Erling Haaland", "0387462822"))
        beneficiary.add(BeneficiaryPhonebookModel("Kevin De Bruyne", "038182332"))
        beneficiary.add(BeneficiaryPhonebookModel("Moisés Caicedo", "038020322"))

        return beneficiary
    }
}