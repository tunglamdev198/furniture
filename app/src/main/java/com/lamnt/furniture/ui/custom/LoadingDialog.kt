package com.lamnt.furniture.ui.custom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import com.lamnt.furniture.databinding.LayoutLoadingBinding


class LoadingDialog(context: Context) : Dialog(context) {
    lateinit var binding: LayoutLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutLoadingBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        setCancelable(false)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}