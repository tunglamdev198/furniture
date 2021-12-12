package com.lamnt.furniture.ui.activity.intro

import android.os.Bundle
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.ActivityIntroBinding
import com.lamnt.furniture.extensions.click
import com.lamnt.furniture.extensions.navigateTo
import com.lamnt.furniture.extensions.setUpTabLayout
import com.lamnt.furniture.model.dto.IntroItem
import com.lamnt.furniture.ui.auth.AuthActivity
import com.lamnt.furniture.ui.base.BaseActivity

class IntroActivity : BaseActivity<ActivityIntroBinding>() {

    private val introAdapter by lazy {
        IntroAdapter()
    }

    override fun getLayoutId(): Int = R.layout.activity_intro

    override fun onViewReady(savedInstance: Bundle?) {
        configViewPager()
        binding.btnGetStarted.click { navigateTo(AuthActivity::class.java, null, true) }
    }

    private fun configViewPager() {
        val items = arrayListOf(
            IntroItem(
                "Fast Choose",
                "The purpose of a text-based is the same as that of a traditional resume — to help you find a job! ",
                R.drawable.ic_intro_1
            ),
            IntroItem(
                "Fast Payment",
                "The mobile phone by itself has many advantages for recruiters. As a result, many recruiters.",
                R.drawable.ic_intro_2
            ),
            IntroItem(
                "Safety",
                "Even if you haven’t had a formal training in layout or the principles of design, there’s a strong chance.",
                R.drawable.ic_intro_3
            )
        )
        introAdapter.data = items
        binding.vgIntro.adapter = introAdapter
        binding.vgIntro.setUpTabLayout(binding.dotIndicator, arrayListOf())
    }
}