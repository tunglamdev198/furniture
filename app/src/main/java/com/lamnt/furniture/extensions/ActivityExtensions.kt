package com.lamnt.furniture.extensions

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lamnt.furniture.MainActivity
import com.lamnt.furniture.R
import com.lamnt.furniture.ui.base.Event
import com.lamnt.furniture.ui.base.EventObserver
import com.lamnt.furniture.utils.PermissionHelper
import java.util.jar.Manifest

/**
 * Replace Fragment with fade anim
 */
fun FragmentActivity.replaceFragment(fragment: Fragment, isAddToBackStack: Boolean) {
    this.supportFragmentManager
        .beginTransaction().apply {
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
            replace(R.id.frameContainer, fragment)
            if (isAddToBackStack)
                addToBackStack(fragment.javaClass.simpleName)
            commit()
        }
}


fun Fragment.replaceFragment(fragment: Fragment, isAddToBackStack: Boolean) {
    requireActivity().replaceFragment(fragment, isAddToBackStack)
}

/**
 * Pop to specific fragment
 */

fun Fragment.backTo(fragmentName: String) {
    requireActivity().supportFragmentManager.popBackStack(fragmentName, 0)
}

/**
 * Pop to top fragment on backstack
 */

fun Fragment.popToTop() {
    requireActivity().supportFragmentManager.popBackStack(
        null,
        FragmentManager.POP_BACK_STACK_INCLUSIVE
    )
}


/**
 * Pop backstack
 */

fun Fragment.popBackStack() {
    requireActivity().supportFragmentManager.popBackStack()
}

/**
 * Remove All Backstack
 */

fun FragmentActivity.removeAllBackStack() {
    for (i in 0 until supportFragmentManager.backStackEntryCount) {
        supportFragmentManager.popBackStack()
    }
}

/**
 * Replace Fragment with slide left to right anim
 */

fun FragmentActivity.replaceFragmentSlide(fragment: Fragment, isAddToBackStack: Boolean) {
    this.supportFragmentManager
        .beginTransaction().apply {
            setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_right,
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            replace(R.id.frameContainer, fragment)
            if (isAddToBackStack)
                addToBackStack(fragment.javaClass.simpleName)
            commit()
        }
}

fun Fragment.replaceFragmentSlide(fragment: Fragment, isAddToBackStack: Boolean) {
    requireActivity().replaceFragmentSlide(fragment, isAddToBackStack)
}

/**
 * Replace Fragment with slide bottom to top anim
 */

fun FragmentActivity.showFragment(fragment: Fragment, isAddToBackStack: Boolean) {
    this.supportFragmentManager
        .beginTransaction().apply {
            setCustomAnimations(
                R.anim.slide_in_up,
                R.anim.slide_out_up
            )
            replace(R.id.frameContainer, fragment)
            if (isAddToBackStack)
                addToBackStack(fragment.javaClass.simpleName)
            commit()
        }
}

/**
 * Show dialog fragment
 */

fun FragmentActivity.showDialogFragment(fragment: DialogFragment) {
    fragment.show(supportFragmentManager, DialogFragment::class.java.simpleName)
}

fun Fragment.showDialogFragment(fragment: DialogFragment) {
    requireActivity().showDialogFragment(fragment)
}

/**
 * Show bottom sheet dialog
 */

fun FragmentActivity.showBottomSheet(fragment: BottomSheetDialogFragment) {
    fragment.show(supportFragmentManager, BottomSheetDialogFragment::class.java.simpleName)
}

fun Activity.navigateTo(clazz: Class<*>, bundle: Bundle?, finishCurrent: Boolean) {
    val intent = Intent(this, clazz)
    bundle?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
    if (finishCurrent) finish()
}

fun Activity.navigateTo(
    clazz: Class<*>,
    bundle: Bundle?,
    finishCurrent: Boolean,
    requestCode: Int
) {
    val intent = Intent(this, clazz)
    bundle?.let {
        intent.putExtras(it)
    }
    startActivityForResult(intent, requestCode)
    if (finishCurrent) finish()
}

fun Activity.navigateTo(clazz: Class<*>, finishCurrent: Boolean) {
    navigateTo(clazz, null, finishCurrent)
}

fun <T> Fragment.observeData(data: LiveData<T>, onChanged: (t: T) -> Unit) {
    data.observe(viewLifecycleOwner, { onChanged(it) })
}

fun <T> Fragment.observeEventData(data: LiveData<Event<T>>, onChanged: (t: T) -> Unit) {
    data.observe(viewLifecycleOwner, EventObserver {
        onChanged(it)
    })
}

/**
 * Make Toast message in Activity
 */

fun Activity.showToast(message: String?) {
    message?.let {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    } ?: run {
        Toast.makeText(this, "Exeption", Toast.LENGTH_SHORT).show()
    }
}

/**
 * Make Toast message in Fragment
 */

fun Fragment.showToast(message: String?) {
    requireActivity().showToast(message)
}

/**
 * Show alert with custom positive action
 */

fun Fragment.showAlert(
    title: String,
    message: String,
    positiveLabel: String,
    onPositiveClick: () -> Unit
) {
    with(AlertDialog.Builder(requireActivity())) {
        setTitle(title)
        setMessage(message)
        setCancelable(false)
        setPositiveButton(positiveLabel) { dialog, _ ->
            run {
                dialog.dismiss()
                onPositiveClick()
            }
        }
        show()
    }
}

/**
 * Show alert show information
 */

fun Fragment.showInfo(
    message: String
) {
    with(AlertDialog.Builder(requireActivity())) {
        setTitle(R.string.app_name)
        setMessage(message)
        setCancelable(false)
        setPositiveButton(R.string.text_ok) { dialog, _ ->
            run {
                dialog.dismiss()
            }
        }
        show()
    }
}

fun Fragment.showInfo(
    message: String,
    onClick: () -> Unit
) {
    with(AlertDialog.Builder(requireActivity())) {
        setTitle(R.string.app_name)
        setMessage(message)
        setCancelable(false)
        setPositiveButton(R.string.text_ok) { dialog, _ ->
            run {
                dialog.dismiss()
                onClick()
            }
        }
        show()
    }
}

fun Fragment.showConfirm(
    message: String,
    onClick: () -> Unit
) {
    with(AlertDialog.Builder(requireActivity())) {
        setTitle(R.string.app_name)
        setMessage(message)
        setCancelable(false)
        setPositiveButton(R.string.text_ok) { dialog, _ ->
            run {
                dialog.dismiss()
                onClick()
            }
        }
        setNegativeButton(R.string.text_cancel) { dialog, _ ->
            run {
                dialog.dismiss()
            }
        }
        show()
    }
}


/**
 * Check runtime permission
 */

fun Fragment.checkPermission(permission: String, callOnGranted: () -> Unit) {
    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                callOnGranted()
            }
        }
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            requireActivity(),
            permission
        ) -> {
            callOnGranted()
        }
        else -> {
            requestPermissionLauncher.launch(
                permission
            )
        }
    }
}

/**
 * Request runtime single permission
 */
fun Fragment.requestPermission(permission: String, code: Int) {
    PermissionHelper.requestPermissions(activity, permission, code)
}

/**
 * Request runtime multi permission
 */

fun Fragment.requestPermission(permission: Array<String>, code: Int) {
    PermissionHelper.requestPermissions(activity, permission, code)
}

/**
 * Change title toolbar
 */

fun MainActivity.changeTitle(@StringRes res: Int) {
    if (res == 0) {
        binding.txtTitle.gone()
    } else {
        binding.txtTitle.text = title
    }
}

fun MainActivity.changeTitle(title: String) {
    if (title.isNullOrEmpty()) {
        binding.txtTitle.gone()
    } else {
        binding.txtTitle.text = title
    }
}
//
///**
// * Handle action left of toolbar
// */
//
//fun MainActivity.buttonLeft(res: Int, action: () -> Unit) {
//    with(binding.btnLeft) {
//        setImageResource(res)
//        click { action() }
//    }
//}
//
/**
 * Check show Toolbar and Bottom bar or not
 */

fun MainActivity.showNavigate(check: Boolean) {
    with(binding) {
        if (check) {
            toolBar.visible()
        } else {
            toolBar.gone()
        }
    }
}

/**
 * Check show Bottom bar or not
 */

fun MainActivity.showBottomBar(check: Boolean) {
    with(binding) {
        if (check) {
            mainBottomNav.visible()
        } else {
            mainBottomNav.gone()
        }
    }
}

/**
 * Check show back button or not
 */

fun MainActivity.showButtonBack(check: Boolean) {
    with(binding) {
        if (check) {
            btnBack.visible()
        } else {
            btnBack.gone()
        }
    }
}

//
///**
// * Check show right action or not
// */
//
//fun MainActivity.showActionRight(check: Boolean) {
//    with(binding.actionRight) {
//        if (check) {
//            visible()
//        } else {
//            gone()
//        }
//    }
//}
//
///**
// * Check show button right action or not
// */
//
//fun MainActivity.showButtonRight(check: Boolean) {
//    with(binding.btnRight) {
//        if (check) {
//            visible()
//        } else {
//            gone()
//        }
//    }
//}
//
//
//fun MainActivity.actionRight(@StringRes name: Int, clickable: () -> Unit) {
//    with(binding.actionRight) {
//        visible()
//        setText(name)
//        click { clickable() }
//    }
//}
//
//
//fun MainActivity.buttonRight(@DrawableRes res: Int, clickable: () -> Unit) {
//    with(binding.btnRight) {
//        visible()
//        setImageResource(res)
//        click { clickable() }
//    }
//}
//
//fun MainActivity.actionEdit(editable: () -> Unit, done: () -> Unit) {
//    with(binding.actionRight) {
//        visible()
//        setText(R.string.text_edit)
//        click {
//            if (getString(R.string.text_edit) == toText()) {
//                setText(R.string.text_done)
//                editable()
//            } else {
//                setText(R.string.text_edit)
//                done()
//            }
//
//        }
//    }
//}
//
//fun MainActivity.actionEditCancel(editable: () -> Unit, cancel: () -> Unit) {
//    with(binding.actionRight) {
//        visible()
//        setText(R.string.text_edit)
//        click {
//            if (getString(R.string.text_edit) == toText()) {
//                setText(R.string.text_cancel)
//                editable()
//            } else {
//                setText(R.string.text_edit)
//                cancel()
//            }
//
//        }
//    }
//}
//
//fun MainActivity.actionEditCancelSelectAll(
//    editable: () -> Unit,
//    cancel: () -> Unit,
//    selectAll: () -> Unit
//) {
//    with(binding.actionRight) {
//        visible()
//        setText(R.string.text_edit)
//        click {
//            if (getString(R.string.text_edit) == toText()) {
//                setText(R.string.text_cancel)
//                binding.btnLeft.gone()
//                binding.actionLeft.visible()
//                editable()
//            } else {
//                binding.btnLeft.visible()
//                binding.actionLeft.gone()
//                setText(R.string.text_edit)
//                cancel()
//            }
//
//        }
//    }
//    with(binding.actionLeft) {
//        setText(R.string.text_select_all)
//        click(selectAll)
//    }
//}
//
fun Fragment.backToMain() {
    if (requireActivity() is MainActivity)
        (requireActivity() as MainActivity).binding.mainBottomNav.selectedItemId = R.id.mnuMain
}


/**
 * Change color of status bar in Activity
 */

fun Activity.changeStatusBarColor(@ColorRes res: Int) {
    window.statusBarColor = ContextCompat.getColor(this, res)
}

/**
 * Change color of status bar in Fragment
 */

fun Fragment.changeStatusBarColor(@ColorRes res: Int) {
    requireActivity().changeStatusBarColor(res)
}

fun Activity.hideSoftKeyboard() {
    currentFocus?.let {
        val inputMethodManager =
            ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Fragment.hiddenKeyboardOutSite(view: View) {
    view.apply {
        isFocusable = true
        isClickable = true
        click { requireActivity().hideSoftKeyboard() }
    }
}