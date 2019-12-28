package org.systers.mentorship.view.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import org.systers.mentorship.R
import org.systers.mentorship.viewmodels.TasksViewModel

class AddTaskFragment : DialogFragment() {

    companion object {
        /**
         * Creates an instance of AddTaskFragment
         */
        fun newInstance() = AddTaskFragment()
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        retainInstance = true
        val taskViewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)

        val builder = AlertDialog.Builder(context)
        val dialogLayout = LayoutInflater.from(context).inflate(R.layout.dialog_add_task, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.etAddTask)

        builder.setTitle(getString(R.string.add_new_task))
        builder.setView(dialogLayout)

        builder.setPositiveButton(getString(R.string.save)) { _, _ ->
            val newTask: String = editText.text.toString()
            taskViewModel.addTask(newTask)
        }
        builder.setNegativeButton(getString(R.string.cancel)) { dialogInterface, _ ->
            dialogInterface.dismiss()
        }

        return builder.create()
    }

    // this prevents the dialog of being closed on orientation change
    override fun onDestroyView() {
        if (dialog != null && retainInstance)
            dialog.setDismissMessage(null)
        super.onDestroyView()
    }

}