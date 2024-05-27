package com.example.em_electronicwallet_frontend.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.em_electronicwallet_frontend.R
import com.example.em_electronicwallet_frontend.databinding.ViewholderGiftBinding
import com.example.em_electronicwallet_frontend.model.GiftModel

class GiftAdapter : RecyclerView.Adapter<GiftAdapter.ViewHolder>() {
    private lateinit var context: Context

    class ViewHolder(val binding: ViewholderGiftBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderGiftBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GiftAdapter.ViewHolder, position: Int) {
        val binding = ViewholderGiftBinding.bind(holder.itemView)

        binding.textTitleGift.text = differ.currentList[position].titleGift
        binding.textExpiryGift.text = "Hạn sử dụng: " + differ.currentList[position].expiryGift
        binding.textNumberLikeGift.text = differ.currentList[position].numberLikeGift.toString()

        Glide.with(holder.itemView.context)
            .load(differ.currentList[position].imageGift)
            .into(binding.imageBackgroundGift)
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<GiftModel>() {
        override fun areItemsTheSame(oldItem: GiftModel, newItem: GiftModel): Boolean {
            return oldItem.titleGift == newItem.titleGift
        }

        override fun areContentsTheSame(oldItem: GiftModel, newItem: GiftModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}