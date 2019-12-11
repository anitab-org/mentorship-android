package org.systers.mentorship.view.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_profile.*
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentProfileBinding
import org.systers.mentorship.models.User
import org.systers.mentorship.utils.EditProfileFragmentErrorStates
import org.systers.mentorship.view.activities.SettingsActivity
import org.systers.mentorship.viewmodels.ProfileViewModel


/**
 * The fragment is responsible for showing the User's profile
 */
class ProfileFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of ProfileFragment
         */
        fun newInstance() = ProfileFragment()

        /**
         * Binding adapter used for setting the right background for EditTexts in this fragment's layout.
         */
        @JvmStatic
        @BindingAdapter("android:background")
        fun loadEditTextBackground(view: View, editing: Boolean) {
            val typedValue = TypedValue()
            val context: Context = view.context
            if (editing) {
                context.theme.resolveAttribute(android.R.attr.editTextBackground, typedValue, true)
                view.setBackgroundResource(typedValue.resourceId)
            } else {
                view.background = null
            }
        }

        val TAG: String = ProfileFragment::class.java.simpleName
        const val ANIM_DURATION = 250L
    }

    private lateinit var fragmentProfileBinding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun getLayoutResourceId(): Int = R.layout.fragment_profile

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentProfileBinding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        return fragmentProfileBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        baseActivity.setSupportActionBar(tbProfile)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            switchAvailableToMentor.buttonTintList =
                ColorStateList.valueOf(ContextCompat.getColor(baseActivity, R.color.colorPrimary))
        }

        profileViewModel = ViewModelProviders.of(activity!!).get(ProfileViewModel::class.java)
        profileViewModel.successfulGet.observe(this, Observer { successful ->
            baseActivity.hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    fragmentProfileBinding.user = profileViewModel.user
                } else {
                    Snackbar.make(
                        fragmentProfileBinding.root, profileViewModel.message,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        })

        profileViewModel.editInProgress.observe(this, Observer { editing ->
            activity?.invalidateOptionsMenu() // Refresh options on toolbar

            fragmentProfileBinding.editing = editing


            imgCamera.isEnabled = editing

            // Animating showing small camera icon
            val scale = if (editing) 1f else 0f
            val animatorX = ObjectAnimator.ofFloat(imgCamera, "scaleX", scale)
            val animatorY = ObjectAnimator.ofFloat(imgCamera, "scaleY", scale)
            animatorX.duration = ANIM_DURATION
            animatorY.duration = ANIM_DURATION

            val scaleDown = AnimatorSet()
            scaleDown.play(animatorX).with(animatorY)
            scaleDown.start()
        })

        baseActivity.showProgressDialog(getString(R.string.fetch_user_profile))
        profileViewModel.getProfile()

        imgCamera.setOnClickListener { Toast.makeText(activity, R.string.not_implemented, Toast.LENGTH_SHORT).show() }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (profileViewModel.editInProgress.value == false)
            inflater.inflate(R.menu.profile_menu, menu)
        else inflater.inflate(R.menu.edit_profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_edit_profile -> {
                profileViewModel.editInProgress.value = true
                true
            }

            R.id.menu_update_profile -> {
                val errors = validateProfileInput(fragmentProfileBinding.user?.name?.trim())

                fun showErrors() {
                    with(fragmentProfileBinding.etName) {
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
                                this.error = null
                                null
                            }
                        }
                    }
                }

                fun createUpdatedUser() = User(
                    name = fragmentProfileBinding.user?.name?.trim(),
                    bio = fragmentProfileBinding.user?.bio,
                    slackUsername = fragmentProfileBinding.user?.slackUsername,
                    location = fragmentProfileBinding.user?.location,
                    occupation = fragmentProfileBinding.user?.occupation,
                    organization = fragmentProfileBinding.user?.organization
                )

                fun showUpdateDialog() {
                    val dialogBuilder = AlertDialog.Builder(baseActivity)
                        .setTitle(R.string.profile_update)
                        .setMessage(R.string.are_you_sure)
                        .setPositiveButton(R.string.update) { _, _ ->
                            profileViewModel.editInProgress.value = false

                            profileViewModel.updateProfile(createUpdatedUser())

                            Snackbar.make(coordinatorProfile, R.string.update_successful, Snackbar.LENGTH_SHORT).show()
                        }.setNegativeButton(R.string.continue_editing) { _, _ -> }

                    dialogBuilder.show()
                }

                if (errors.isEmpty()) {
                    showUpdateDialog()
                } else showErrors()
                true
            }

            R.id.menu_cancel_update -> {
                fun showCancelUpdateDialog() {
                    val dialogBuilder = AlertDialog.Builder(baseActivity)
                        .setTitle(R.string.cancel_profile_update)
                        .setMessage(R.string.are_you_sure)
                        .setPositiveButton(R.string.cancel) { _, _ ->
                            profileViewModel.editInProgress.value = false

                            // Reset all changes
                            fragmentProfileBinding.user = profileViewModel.user

                            Snackbar.make(coordinatorProfile, R.string.cancelled, Snackbar.LENGTH_SHORT).show()
                        }.setNegativeButton(R.string.continue_editing) { _, _ -> }

                    dialogBuilder.show()
                }

                showCancelUpdateDialog()
                true
            }

            R.id.menu_settings -> {
                startActivity(Intent(context, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    override fun onDestroy() {
        super.onDestroy()

        profileViewModel.successfulGet.removeObservers(activity!!)
        profileViewModel.successfulGet.value = null
        profileViewModel.editInProgress.value = false
    }
}
