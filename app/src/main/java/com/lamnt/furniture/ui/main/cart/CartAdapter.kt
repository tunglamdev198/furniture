package com.lamnt.furniture.ui.main.cart

import com.lamnt.furniture.R
import com.lamnt.furniture.data.database.entities.ProductionEntity
import com.lamnt.furniture.databinding.ItemCartBinding
import com.lamnt.furniture.extensions.click
import com.lamnt.furniture.ui.base.BaseAdapter

class CartAdapter : BaseAdapter<ProductionEntity, ItemCartBinding>() {
    override val itemLayout: Int
        get() = R.layout.item_cart

    override fun bind(binding: ItemCartBinding, data: ProductionEntity, position: Int) {
        binding.production = data
        binding.tvTotalPrice.text =
            String.format("%.2f", (data.amount * data.price)).replace(",", ".")
        binding.btnMinus.click {
            onItemClickListener?.onItemClick(binding.btnMinus, data, position)
        }

        binding.btnPlus.click {
            onItemClickListener?.onItemClick(binding.btnPlus, data, position)
        }
    }
}