package com.example.em_electronicwallet_frontend.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.em_electronicwallet_frontend.databinding.ViewholderBeneficiaryRecentlyBinding
import com.example.em_electronicwallet_frontend.model.BeneficiaryRecentlyModel
import java.text.DecimalFormat

class BeneficiaryRecentlyAdapter : RecyclerView.Adapter<BeneficiaryRecentlyAdapter.ViewHolder>() {
    private lateinit var context: Context

    class ViewHolder(val binding:ViewholderBeneficiaryRecentlyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BeneficiaryRecentlyAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderBeneficiaryRecentlyBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BeneficiaryRecentlyAdapter.ViewHolder, position: Int) {
        val binding = ViewholderBeneficiaryRecentlyBinding.bind(holder.itemView)

        binding.textNameBeneficiary.text = differ.currentList[position].nameBeneficiary
        binding.textNumberAccount.text = differ.currentList[position].numberBeneficiary

        val decimalFormat = DecimalFormat("#,###")
        binding.textNumberMoney.text = decimalFormat.format(differ.currentList[position].numberMoney) + " VNĐ"
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<BeneficiaryRecentlyModel>() {
        override fun areItemsTheSame(
            oldItem: BeneficiaryRecentlyModel,
            newItem: BeneficiaryRecentlyModel
        ): Boolean {
            return oldItem.nameBeneficiary == newItem.nameBeneficiary
        }

        override fun areContentsTheSame(
            oldItem: BeneficiaryRecentlyModel,
            newItem: BeneficiaryRecentlyModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}