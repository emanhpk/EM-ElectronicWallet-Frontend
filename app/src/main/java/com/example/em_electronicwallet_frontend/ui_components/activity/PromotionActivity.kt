package com.example.em_electronicwallet_frontend.ui_components.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.em_electronicwallet_frontend.adapter.GiftAdapter
import com.example.em_electronicwallet_frontend.databinding.ActivityPromotionBinding
import com.example.em_electronicwallet_frontend.model.GiftModel

class PromotionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPromotionBinding

    private val giftAdapter by lazy { GiftAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromotionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val list = loadData()
        giftAdapter.differ.submitList(list)
        binding.listGift.apply {
            layoutManager = LinearLayoutManager(this@PromotionActivity)
            adapter = giftAdapter
        }
    }

    private fun loadData() : MutableList<GiftModel> {
        val giftModel: MutableList<GiftModel> = mutableListOf()
        giftModel.add(GiftModel(
            "android.resource://com.example.em_electronicwallet_frontend/drawable/image_refund_money",
            "Hoàn tiền", "24/02/2025", 24023))
        giftModel.add(GiftModel(
            "android.resource://com.example.em_electronicwallet_frontend/drawable/image_gift_tv",
            "Mua gói cước truyền hình", "06/09/2024", 12403))
        giftModel.add(GiftModel(
            "android.resource://com.example.em_electronicwallet_frontend/drawable/image_course_promotion",
            "Khuyến mãi mua sách", "06/06/2024", 6202))
        giftModel.add(GiftModel(
            "android.resource://com.example.em_electronicwallet_frontend/drawable/image_phone_recharge",
            "Nạp tiền điện thoại mệnh giá 10,000 VNĐ", "12/12/2025", 5111))
        giftModel.add(GiftModel(
            "android.resource://com.example.em_electronicwallet_frontend/drawable/image_data_recharge",
            "Khuyến mãi 100GB data", "12/12/2025", 4002))

        return giftModel
    }
}