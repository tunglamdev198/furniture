package com.lamnt.furniture.ui.activity.intro

import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.ItemIntroBinding
import com.lamnt.furniture.model.dto.IntroItem
import com.lamnt.furniture.ui.base.BaseAdapter

class IntroAdapter : BaseAdapter<IntroItem, ItemIntroBinding>() {
    override val itemLayout: Int
        get() = R.layout.item_intro

    override fun bind(binding: ItemIntroBinding, data: IntroItem, position: Int) {
        binding.introItem = data
    }
}