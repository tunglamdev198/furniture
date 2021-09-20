package com.lamnt.furniture.extensions

import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lamnt.furniture.ui.base.BaseAdapter

/**
 * Gone a View
 */

fun View.gone() {
    this.visibility = View.GONE
}

/**
 * Visible a View
 */

fun View.visible() {
    this.visibility = View.VISIBLE
}

/**
 * Invisible a View
 */

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

/**
 * Set text color with color res
 */


fun TextView.setColorInt(res: Int) {
    this.setTextColor(ContextCompat.getColor(context, res))
}

/**
 * Set background color of View with color res
 */

fun View.setBackgroundColorInt(res: Int) {
    this.setBackgroundColor(ContextCompat.getColor(context, res))
}

/**
 * Get text from TextView
 */

fun TextView.toText(): String {
    return this.text.toString().trim()
}

fun TextView.leftDrawable(@DrawableRes id: Int = 0) {
    this.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0)
}

fun TextView.rightDrawable(@DrawableRes id: Int = 0) {
    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, id, 0)
}


/**
 * Set text and set cursor to end of content
 */

fun EditText.applyText(text: String) {
    this.setText(text)
    setSelection(text.length)
}

/**
 * Set image of ImageView by name
 */

fun ImageView.setImgResByName(name: String?) {
    name?.let {
        Glide.with(context)
            .load(resources.getIdentifier(it, "drawable", context.packageName))
            .into(this)
    }
}

/**
 * Load Image with Uri
 */

fun ImageView.loadUriImage(uri: Uri) {
    Glide.with(context)
        .load(uri)
        .into(this)
}

/**
 * Set up vertical recycler view with BaseRecyclerViewAdapter
 */

fun <D, VB : ViewDataBinding, A : BaseAdapter<D, VB>>
        RecyclerView.setupVertical(recyclerViewAdapter: A) {
    apply {
        layoutManager = LinearLayoutManager(context)
        adapter = recyclerViewAdapter
    }
}

fun <D, VB : ViewDataBinding, A : BaseAdapter<D, VB>>
        RecyclerView.setupHorizontal(recyclerViewAdapter: A) {
    apply {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = recyclerViewAdapter
    }
}

/**
 * Set up GridLayout recycler view with BaseRecyclerViewAdapter
 */

fun <D, VB : ViewDataBinding, A : BaseAdapter<D, VB>>
        RecyclerView.setupGrid(recyclerViewAdapter: A, column: Int) {
    apply {
        layoutManager = GridLayoutManager(context, column)
        adapter = recyclerViewAdapter
    }
}

/**
 * Set up vertical recycler view with RecyclerViewAdapter
 */

fun RecyclerView.setupVertical(recyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
    apply {
        layoutManager = LinearLayoutManager(context)
        adapter = recyclerViewAdapter
    }
}

/**
 * Set up ViewPager2 with Tablayout
 */

fun ViewPager2.setUpTabLayout(tabLayout: TabLayout, titles: List<String>) {
    TabLayoutMediator(tabLayout, this) { tab, position ->
        if (titles.isNotEmpty()) {
            tab.text = titles[position]
        }
        setCurrentItem(tab.position, true)
    }.attach()
}

/**
 * Handle Onclick Listener
 */

fun View.click(void: () -> Unit) {
    setOnClickListener {
        void()
    }
}

/**
 * Handle Onclick Listener with rotate anim
 */

fun View.rotateClick(void: () -> Unit) {
    click {
        val rotate = ObjectAnimator.ofFloat(this, "rotation", 180f, 0f)
        rotate.duration = 200
        rotate.start()
        void()
    }
}

/**
 * Create data binding mapper
 */

fun createBinding(parent: ViewGroup, @LayoutRes layoutRes: Int): ViewDataBinding {
    return DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        layoutRes,
        parent,
        false
    )
}

// Rotate the image from Landscape to Portrait
fun Bitmap.rotate(degree: Int): Bitmap {
    // Initialize a new matrix
    val matrix = Matrix()

    // Rotate the bitmap
    matrix.postRotate(degree.toFloat())

    // Resize the bitmap
    val scaledBitmap = Bitmap.createScaledBitmap(
        this,
        width,
        height,
        true
    )

    // Create and return the rotated bitmap
    return Bitmap.createBitmap(
        scaledBitmap,
        0,
        0,
        scaledBitmap.width,
        scaledBitmap.height,
        matrix,
        true
    )
}

fun View.hideKeyboard() {
    val inputMethodManager =
        context!!.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
}