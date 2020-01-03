package org.systers.mentorship.view.fragments


import android.app.DatePickerDialog
import android.app.Dialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.EditProfileFragmentErrorStates
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.viewmodels.ProfileViewModel
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * The fragment is responsible for editing the User's profile
 */
class EditProfileFragment: DialogFragment() {

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

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var editProfileBinding: FragmentEditProfileBinding

    private lateinit var currentUser: User

    private var date = ""
    private val calendar = Calendar.getInstance()
    private val maxDate by lazy {
        calendar.add(Calendar.YEAR, Constants.MIN_AGE)
        return@lazy calendar.timeInMillis
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        profileViewModel = ViewModelProviders.of(activity!!).get(ProfileViewModel::class.java)
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

        if (profileViewModel.user.birthday != null) {
            val sdf = SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault())
            editProfileBinding.tiDateOfBirth.editText
                    ?.setText(sdf.format(profileViewModel.user.birthday))
        }

        //needed to auto-insert slashes(/)
        editProfileBinding.tiDateOfBirth.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    //if user entered/deleted day or month, add  or remove /
                    if (s.length == 2 || s.length == 5)
                        if (s.length > date.length)
                            editProfileBinding.tiDateOfBirth.editText?.text?.append("/")
                        else {
                            editProfileBinding.tiDateOfBirth.editText?.setText(s.dropLast(1))
                            editProfileBinding.tiDateOfBirth.editText?.setSelection(s.length - 1)
                        }
                    date = editProfileBinding.tiDateOfBirth.editText?.text.toString()
                }
            }
        })

        val d = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(year, month, day)
            val sdf = SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault())
            editProfileBinding.tiDateOfBirth.editText?.setText(sdf.format(calendar.time))
        }
        editProfileBinding.ivCalendar.setOnClickListener {
            val validateDate = validateDate(false)
            val day = if (validateDate) date.take(2).toInt() else calendar.get(Calendar.DAY_OF_MONTH)
            val month = if (validateDate) date.substring(3, 5).toInt().dec() else calendar.get(Calendar.MONTH)
            val year = if (validateDate) date.takeLast(4).toInt() else calendar.get(Calendar.YEAR)

            val dialog = DatePickerDialog(requireContext(), d, year, month, day)
            dialog.datePicker.maxDate = maxDate
            dialog.show()
        }

        val editProfileDialog = dialog as AlertDialog

        editProfileDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val errors = validateProfileInput(editProfileBinding.user?.name?.trim())

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
            if (validateDate(true) && currentUser != editProfileBinding.user && errors.isEmpty()) {
                profileViewModel.updateProfile(editProfileBinding.user!!)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        profileViewModel.successfulUpdate.removeObservers(activity!!)
        profileViewModel.successfulUpdate.value = null
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

    private fun validateDate(showErrors: Boolean): Boolean {
        var isValid = true
        if (date.length < 10) {
            if (showErrors) editProfileBinding.tiDateOfBirth.error = getString(R.string.enter_whole_date)
            isValid = false
        } else {
            if (showErrors) editProfileBinding.tiDateOfBirth.error = null

            if (date.takeLast(4).toInt() < Constants.MAX_BIRTHDAY_YEAR) {
                if (showErrors) editProfileBinding.tiDateOfBirth.error = getString(R.string.entered_incorrect_date)
                isValid = false
            }

            date = editProfileBinding.tiDateOfBirth.editText?.text.toString()
            try {
                val dateFormat: DateFormat = SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault())
                dateFormat.isLenient = false
                val d = dateFormat.parse(date)
                editProfileBinding.user?.birthday = d.time.toFloat()
                if (maxDate < d.time) {
                    if (showErrors) editProfileBinding.tiDateOfBirth.error = getString(R.string.min_age_error, -Constants.MIN_AGE)
                    isValid = false
                }
            } catch (e: ParseException) {
                if (showErrors) editProfileBinding.tiDateOfBirth.error = getString(R.string.entered_incorrect_date)
                isValid = false
            }
        }
        return isValid
    }

}
