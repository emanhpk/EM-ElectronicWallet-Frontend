package com.example.em_electronicwallet_frontend.ui_components.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.em_electronicwallet_frontend.adapter.BeneficiaryRecentlyAdapter
import com.example.em_electronicwallet_frontend.databinding.FragmentBeneficiaryRecentlyBinding
import com.example.em_electronicwallet_frontend.model.BeneficiaryRecentlyModel

class BeneficiaryRecentlyFragment : Fragment() {
    private var _binding: FragmentBeneficiaryRecentlyBinding? = null

    private val binding get() = _binding!!

    private val beneficiaryRecentlyAdapter by lazy { BeneficiaryRecentlyAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeneficiaryRecentlyBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        val list = loadData()
        beneficiaryRecentlyAdapter.differ.submitList(list)
        binding.beneficiaryRecentlyView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = beneficiaryRecentlyAdapter
        }
    }

    private fun loadData() : MutableList<BeneficiaryRecentlyModel> {
        val beneficiary: MutableList<BeneficiaryRecentlyModel> = mutableListOf()
        beneficiary.add(BeneficiaryRecentlyModel("Cristiano Ronaldo", "0387462833", 1500000))
        beneficiary.add(BeneficiaryRecentlyModel("Jude Bellingham", "0380102232", 2400000))
        beneficiary.add(BeneficiaryRecentlyModel("Cole Palmer", "0387482883", 1000000))
        beneficiary.add(BeneficiaryRecentlyModel("Lionel Messi", "0388273221", 500000))
        beneficiary.add(BeneficiaryRecentlyModel("Erling Haaland", "0387462822", 2500000))
        beneficiary.add(BeneficiaryRecentlyModel("Jamal Musiala", "0387918223", 600000))
        beneficiary.add(BeneficiaryRecentlyModel("Kylian Mbappé", "0380192783", 300000))
        beneficiary.add(BeneficiaryRecentlyModel("Kevin De Bruyne", "038182332", 900000))
        beneficiary.add(BeneficiaryRecentlyModel("Frank Lampard", "0387991833", 20000))
        beneficiary.add(BeneficiaryRecentlyModel("John Terry", "0387461113", 600000))
        beneficiary.add(BeneficiaryRecentlyModel("Moisés Caicedo", "038020322", 7500000))
        beneficiary.add(BeneficiaryRecentlyModel("Michael Ballack", "0387401033", 1000000))
        beneficiary.add(BeneficiaryRecentlyModel("Didier Drogba", "0387461113", 20000))
        beneficiary.add(BeneficiaryRecentlyModel("Federico Chiesa", "0387419923", 800000))
        beneficiary.add(BeneficiaryRecentlyModel("Bukayo Saka", "0387100233", 160000))

        return beneficiary
    }
}