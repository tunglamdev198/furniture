package com.lamnt.furniture.ui.main.home

import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.ItemHomeProductionBinding
import com.lamnt.furniture.model.dto.Production
import com.lamnt.furniture.ui.base.BaseAdapter

class HomeProductionAdapter : BaseAdapter<Production, ItemHomeProductionBinding>() {
    override val itemLayout: Int
        get() = R.layout.item_home_production

    override fun bind(binding: ItemHomeProductionBinding, data: Production, position: Int) {
        binding.production = data
    }
}