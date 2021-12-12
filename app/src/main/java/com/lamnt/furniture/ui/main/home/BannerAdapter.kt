package com.lamnt.furniture.ui.main.home

import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.ItemBannerBinding
import com.lamnt.furniture.model.dto.Banner
import com.lamnt.furniture.ui.base.BaseAdapter

class BannerAdapter : BaseAdapter<Banner, ItemBannerBinding>() {
    override val itemLayout: Int
        get() = R.layout.item_banner

    override fun bind(binding: ItemBannerBinding, data: Banner, position: Int) {
        binding.banner = data
    }
}