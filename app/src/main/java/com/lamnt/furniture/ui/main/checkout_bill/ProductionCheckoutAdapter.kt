package com.lamnt.furniture.ui.main.checkout_bill

import com.lamnt.furniture.R
import com.lamnt.furniture.data.database.entities.ProductionEntity
import com.lamnt.furniture.databinding.ItemProductCheckoutBinding
import com.lamnt.furniture.ui.base.BaseAdapter

class ProductionCheckoutAdapter : BaseAdapter<ProductionEntity, ItemProductCheckoutBinding>() {
    override val itemLayout: Int
        get() = R.layout.item_product_checkout

    override fun bind(binding: ItemProductCheckoutBinding, data: ProductionEntity, position: Int) {
        binding.production = data
    }
}