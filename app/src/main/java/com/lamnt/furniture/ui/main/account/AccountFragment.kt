package com.lamnt.furniture.ui.main.account

import android.os.Bundle
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentAccountBinding
import com.lamnt.furniture.extensions.*
import com.lamnt.furniture.ui.activity.MainActivity
import com.lamnt.furniture.ui.auth.AuthActivity
import com.lamnt.furniture.ui.base.BaseFragmentMVVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : BaseFragmentMVVM<FragmentAccountBinding, AccountViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_account

    override fun getViewModelClazz(): Class<AccountViewModel> = AccountViewModel::class.java

    override fun onViewReady(savedInstance: Bundle?) {
        if (viewModel.isLoggedIn) {
            binding.layoutAccount.visible()
            binding.layoutLogin.gone()
        } else {
            binding.layoutAccount.gone()
            binding.layoutLogin.visible()
        }
        binding.btnLogin.click { requireActivity().navigateTo(AuthActivity::class.java, true) }
        binding.btnLogout.click {
            showConfirm("Do you want to logout?") {
                viewModel.logout()
                requireActivity().navigateTo(AuthActivity::class.java, true)
            }
        }

        binding.btnEdit.click {
            replaceFragment(EditAccountFragment(), true)
        }
    }

    override fun initSubscriber() {
        observeData(viewModel.user) {
            it?.let {
                binding.user = it
                shareViewModel.user.value = it
            }
        }
    }

    override fun onResume() {
        super.onResume()
        with((requireActivity() as MainActivity)) {
            showBottomBar(true)
            changeTitle("My Account")
        }

    }
}