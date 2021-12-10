package com.lamnt.furniture.ui.main.home

import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.ItemHomeProductionBinding
import com.lamnt.furniture.extensions.click
import com.lamnt.furniture.model.dto.Production
import com.lamnt.furniture.ui.base.BaseAdapter
import com.lamnt.furniture.ui.base.OnItemClickListener

class HomeProductionAdapter : BaseAdapter<Production, ItemHomeProductionBinding>() {
    override val itemLayout: Int
        get() = R.layout.item_home_production

    override fun bind(binding: ItemHomeProductionBinding, data: Production, position: Int) {
        binding.production = data
        binding.root.click { onItemClickListener?.onItemClick(binding.root, data, position) }
    }


}