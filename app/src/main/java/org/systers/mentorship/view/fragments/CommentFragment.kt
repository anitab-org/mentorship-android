package org.systers.mentorship.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_mentorship_comments.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.requests.CreateComment
import org.systers.mentorship.view.adapters.CommentsAdapter
import org.systers.mentorship.viewmodels.CommentViewModel

class CommentFragment(
        private var mentorshipRelation: Relationship,
        private var taskId: Int
): BaseFragment() {

    companion object {
        /**
         * Creates an instance of [CommentFragment]
         */
        fun newInstance(
                mentorshipRelation: Relationship,
                taskId: Int
        ) = CommentFragment(mentorshipRelation, taskId)
        val TAG = CommentFragment::class.java.simpleName
    }

    private val viewModel: CommentViewModel by viewModels()

    override fun getLayoutResourceId(): Int = R.layout.fragment_mentorship_comments

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupCommentPost()
        Log.i("TASK", taskId.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getComments(mentorshipRelation.id, taskId)
    }

    private fun setupObservers() {
        viewModel.successfulGet.observe(viewLifecycleOwner, Observer {
            Log.i("viewmodel", it.toString())
            if (it != null) {
                rvComments.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = CommentsAdapter(requireContext(), it)
                }
            } else Toast.makeText(requireContext(), viewModel.message, Toast.LENGTH_SHORT).show()
        })

        viewModel.successfulAdd.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.getComments(mentorshipRelation.id, taskId)
                comment_et.text.clear()
            }
            else Toast.makeText(requireContext(), viewModel.message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupCommentPost() {
        sendCommentBtn.setOnClickListener {
            if (comment_et.text.isNotEmpty()) {
                viewModel.addComment(
                    mentorshipRelation.id,
                    taskId,
                    CreateComment(comment = comment_et.text.toString())
                )
            } else Snackbar.make(requireView(),"Please enter something to comment", Snackbar.LENGTH_LONG).show()
        }
    }
}