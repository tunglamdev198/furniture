package com.lamnt.furniture.ui.base

import android.view.View


interface OnItemClickListener<T> {
    fun onItemClick(view: View?, data: T, position: Int)
}
