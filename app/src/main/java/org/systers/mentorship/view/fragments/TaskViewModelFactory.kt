package org.systers.mentorship.view.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.viewmodels.TasksViewModel

class TaskViewModelFactory(private val mentorshipRelation: Relationship) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return TasksViewModel(mentorshipRelation) as T
    }

}