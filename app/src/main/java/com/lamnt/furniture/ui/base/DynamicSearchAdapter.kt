package com.lamnt.furniture.ui.base

import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.ViewDataBinding
import java.util.*
import kotlin.collections.ArrayList

abstract class DynamicSearchAdapter<T : DynamicSearchAdapter.Searchable, V : ViewDataBinding>(list: List<T>) :
    BaseAdapter<T, V>(), Filterable {
    private val originalList = ArrayList<T>(list)
    private var onNothingFound: (() -> Unit)? = null

    /**
     * Searches a specific item in the list and updates adapter.
     * if the search returns empty then onNothingFound callback is invoked if provided which can be used to update UI
     * @param s the search query or text. It can be null.
     * @param onNothingFound a method-body to invoke when search returns nothing. It can be null.
     */
    fun search(s: String?, onNothingFound: (() -> Unit)?) {
        this.onNothingFound = onNothingFound
        filter.filter(s)

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                data.clear()
                if (constraint.isNullOrBlank()) {
                    data.addAll(originalList)
                } else {
                    val searchResults =
                        originalList.filter {
                            it.getSearchCriteria()
                                .contains(constraint.toString().toLowerCase(Locale.getDefault()))
                        }
                    data.addAll(searchResults)
                }
                return filterResults.also {
                    it.values = data
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (data.isNullOrEmpty())
                    onNothingFound?.invoke()
                notifyDataSetChanged()

            }
        }
    }

    interface Searchable {
        fun getSearchCriteria(): String
    }
}