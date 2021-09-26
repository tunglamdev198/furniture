package com.lamnt.furniture.ui.main.home

import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.ItemHomeCategoryBinding
import com.lamnt.furniture.model.Category
import com.lamnt.furniture.ui.base.BaseAdapter

class CategoriesAdapter : BaseAdapter<Category, ItemHomeCategoryBinding>() {
    override val itemLayout: Int
        get() = R.layout.item_home_category

    override fun bind(binding: ItemHomeCategoryBinding, data: Category, position: Int) {

    }
}