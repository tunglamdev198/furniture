package com.lamnt.furniture.ui.main.account

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.lamnt.furniture.R
import com.lamnt.furniture.databinding.FragmentEditProfileBinding
import com.lamnt.furniture.extensions.*
import com.lamnt.furniture.ui.activity.MainActivity
import com.lamnt.furniture.ui.base.BaseFragment
import java.util.*
import kotlin.text.isNullOrEmpty

class EditAccountFragment : BaseFragment<FragmentEditProfileBinding>() {
    private val AUTOCOMPLETE_REQUEST_CODE = 100
    override fun getLayoutId(): Int = R.layout.fragment_edit_profile

    override fun onViewReady(savedInstance: Bundle?) {
        if (!Places.isInitialized()) {
            Places.initialize(
                requireContext().applicationContext,
                getString(R.string.place_api_key),
                Locale.US
            )
        }
        shareViewModel.user.value?.let {
            binding.user = it
        }
        binding.txtAddress.click {
            val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)

            // Start the autocomplete intent.
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(requireActivity())
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
        }

        binding.txtBirthday.click {
            showDatePicker(binding.txtBirthday.toText())
        }
    }

    override fun onResume() {
        super.onResume()

        with((requireActivity() as MainActivity)) {
            showBottomBar(false)
            changeTitle("Edit Profile")
        }

    }

    @SuppressLint("SetTextI18n")
    private fun showDatePicker(currentDate: String?) {
        val year: Int
        val month: Int
        val day: Int
        if (!currentDate.isNullOrEmpty()) {
            val arrDate = currentDate.split("/")
            year = arrDate[0].toInt()
            month = arrDate[1].toInt() - 1
            day = arrDate[2].toInt()
        } else {
            val c = Calendar.getInstance()
            year = c.get(Calendar.YEAR)
            month = c.get(Calendar.MONTH)
            day = c.get(Calendar.DAY_OF_MONTH)
        }
        val dpd = DatePickerDialog(
            requireActivity(),
            { view, selectedYear, monthOfYear, dayOfMonth ->
                binding.txtBirthday.text =
                    "$selectedYear/${(monthOfYear + 1).time()}/${dayOfMonth.time()}"
            },
            year,
            month,
            day
        )

        dpd.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        Log.i("TAG", "Place: ${place.name}, ${place.id}")
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        status.statusMessage?.let { it1 -> Log.i("TAG", it1) }
                    }
                }
                Activity.RESULT_CANCELED -> {
                    Log.i("TAG", "Canceled")
                }
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}