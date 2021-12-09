package com.lamnt.furniture.ui.main.cart

import com.lamnt.furniture.ui.base.BaseAdapter
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.ItemCartBinding
import com.lamnt.furniture.model.dto.Production

class CartAdapter : BaseAdapter<Production, ItemCartBinding>(){
    override val itemLayout: Int
        get() = R.layout.item_cart

    override fun bind(binding: ItemCartBinding, data: Production, position: Int) {
        binding.production = data
    }
}