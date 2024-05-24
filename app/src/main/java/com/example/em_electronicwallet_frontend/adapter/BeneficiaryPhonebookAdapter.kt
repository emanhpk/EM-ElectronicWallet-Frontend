package com.example.em_electronicwallet_frontend.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.em_electronicwallet_frontend.databinding.ViewholderBeneficiaryPhonebookBinding
import com.example.em_electronicwallet_frontend.model.BeneficiaryPhonebookModel

class BeneficiaryPhonebookAdapter : RecyclerView.Adapter<BeneficiaryPhonebookAdapter.ViewHolder>() {
    private lateinit var context: Context

    class ViewHolder(val binding: ViewholderBeneficiaryPhonebookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BeneficiaryPhonebookAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderBeneficiaryPhonebookBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeneficiaryPhonebookAdapter.ViewHolder, position: Int) {
        val binding = ViewholderBeneficiaryPhonebookBinding.bind(holder.itemView)

        binding.textNameBeneficiary.text = differ.currentList[position].nameBeneficiary
        binding.textNumberAccount.text = differ.currentList[position].numberBeneficiary
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<BeneficiaryPhonebookModel>() {
        override fun areItemsTheSame(
            oldItem: BeneficiaryPhonebookModel,
            newItem: BeneficiaryPhonebookModel
        ): Boolean {
            return oldItem.nameBeneficiary == newItem.nameBeneficiary
        }

        override fun areContentsTheSame(
            oldItem: BeneficiaryPhonebookModel,
            newItem: BeneficiaryPhonebookModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}