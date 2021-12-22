package com.lamnt.furniture.ui.main.account

import android.os.Bundle
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentEditProfileBinding
import com.lamnt.furniture.extensions.changeTitle
import com.lamnt.furniture.extensions.showBottomBar
import com.lamnt.furniture.ui.activity.MainActivity
import com.lamnt.furniture.ui.base.BaseFragment

class EditAccountFragment : BaseFragment<FragmentEditProfileBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_edit_profile

    override fun onViewReady(savedInstance: Bundle?) {

    }

    override fun onResume() {
        super.onResume()

        with((requireActivity() as MainActivity)) {
            showBottomBar(false)
            changeTitle("Edit Profile")
        }

    }
}