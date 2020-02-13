package org.systers.mentorship.view.fragments


import android.app.DatePickerDialog
import android.app.Dialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentEditProfileBinding
import org.systers.mentorship.models.User
import org.systers.mentorship.utils.EditProfileFragmentErrorStates
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.viewmodels.ProfileViewModel
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * The fragment is responsible for editing the User's profile
 */
class EditProfileFragment: DialogFragment() {

    private lateinit var calendarMinAge: Calendar
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    companion object {
        private lateinit var tempUser: User
        /**
         * Creates an instance of EditProfileFragment
         */
        fun newInstance(user: User): EditProfileFragment {
            tempUser = user.copy(id = null, username = null, email = null)
            return EditProfileFragment()
        }
    }

    private val profileViewModel by lazy {
        ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }
    private lateinit var editProfileBinding: FragmentEditProfileBinding

    private lateinit var currentUser: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        profileViewModel.successfulUpdate.observe(this, Observer { successful ->
            (activity as MainActivity).hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    Toast.makeText(context, getText(R.string.update_successful), Toast.LENGTH_LONG).show()
                    profileViewModel.getProfile()
                    dismiss()
                } else {
                    Toast.makeText(activity, profileViewModel.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        isCancelable = false
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        editProfileBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.fragment_edit_profile, null, false)

        editProfileBinding.user = tempUser.copy()
        currentUser = tempUser.copy()

        val dialogBuilder = AlertDialog.Builder(context!!)
        dialogBuilder.setView(editProfileBinding.root)
        dialogBuilder.setTitle(R.string.fragment_title_edit_profile)
        dialogBuilder.setPositiveButton(getString(R.string.save), null)
        dialogBuilder.setNegativeButton(getString(R.string.cancel)) { _, _ -> }

        return dialogBuilder.create()
    }

    override fun onResume() {
        super.onResume()

        val editProfileDialog = dialog as AlertDialog

        editProfileBinding.calendarButton.setOnClickListener {
            // max date: 13 years earlier from now
            // user must be minimum 13 years old
            calendarMinAge = Calendar.getInstance()
            calendarMinAge.add(Calendar.YEAR, -13)
            val calendarDoB = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(context,
                    DatePickerDialog.OnDateSetListener { _, year, month, day ->
                        calendarDoB.set(year, month, day)

                        val formattedDate = dateFormatter.format(calendarDoB.time)
                        editProfileBinding.tiDoB.editText?.setText(formattedDate)
                    }, //set default datePicker date to max possible date
                    calendarMinAge.get(Calendar.YEAR),
                    calendarMinAge.get(Calendar.MONTH),
                    calendarMinAge.get(Calendar.DATE)
            )
            //lock max date
            datePickerDialog.datePicker.maxDate = calendarMinAge.timeInMillis
            datePickerDialog.show()
        }

        editProfileDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val errors = validateProfileInput(editProfileBinding.user?.name?.trim())

            val dateValidity = validateDate(
                    editProfileBinding.user?.dateOfBirth?.trim().toString()
            )

            with(editProfileBinding.tiName) {
                this.error = when (errors.firstOrNull()) {
                    is EditProfileFragmentErrorStates.EmptyNameError ->
                        context.getString(R.string.error_empty_name)
                    is EditProfileFragmentErrorStates.NameTooShortError -> {
                        val minLength = resources.getInteger(R.integer.min_name_length)
                        context.getString(R.string.error_name_too_short, minLength)
                    }
                    is EditProfileFragmentErrorStates.NameTooLongError -> {
                        val maxLength = resources.getInteger(R.integer.max_name_length)
                        context.getString(R.string.error_name_too_long, maxLength)
                    }
                    else -> {
                        this.isErrorEnabled = false
                        null
                    }
                }
            }
            if (currentUser != editProfileBinding.user && errors.isEmpty() && dateValidity) {
                profileViewModel.updateProfile(editProfileBinding.user!!)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        profileViewModel.successfulUpdate.removeObservers(activity!!)
        profileViewModel.successfulUpdate.value = null
    }

    private fun validateDate(dateOfBirth: String): Boolean {
        var isValid: Boolean
        if (dateOfBirth == "null") {
            editProfileBinding.tiDoB.error = getString(R.string.error_empty_DoB)
            isValid = false
        }
        else
        {
            val calendarDoB = Calendar.getInstance()
            try {
                calendarDoB.time = dateFormatter.parse(dateOfBirth)
                calendarMinAge = Calendar.getInstance()
                calendarMinAge.add(Calendar.YEAR, -13)
                //time difference must be greater than or equal to 13 years
                if (calendarDoB.time > calendarMinAge.time){
                    //less than 13, error
                    editProfileBinding.tiDoB.error = getString(R.string.error_underage_DoB)
                    isValid = false
                }
                else {
                    editProfileBinding.tiDoB.error = null
                    isValid = true
                }
            }
            catch (exception: Exception){
                editProfileBinding.tiDoB.error = getString(R.string.error_invalid_DoB)
                isValid = false
            }
        }
        return isValid
    }

    private fun validateProfileInput(name: String?): Array<EditProfileFragmentErrorStates> {

        var errors = arrayOf<EditProfileFragmentErrorStates>()

        if (name.isNullOrEmpty()) errors += EditProfileFragmentErrorStates.EmptyNameError

        if (name?.length ?: 0 < resources.getInteger(R.integer.min_name_length)) {
            errors += EditProfileFragmentErrorStates.NameTooShortError
        }

        if (name?.length ?: 0 > resources.getInteger(R.integer.max_name_length)) {
            errors += EditProfileFragmentErrorStates.NameTooLongError
        }
        return errors
    }
}
