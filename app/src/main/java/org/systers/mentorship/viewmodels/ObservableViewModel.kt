package org.systers.mentorship.viewmodels

import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import com.android.databinding.library.baseAdapters.BR

/**
 * This ViewModel allows to have observable fields without having to use ObservableFields
 */
abstract class ObservableViewModel : ViewModel(), Observable {

    @delegate:Transient
    private val mCallbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.remove(callback)
    }

    /**
     * This helps fields from a ViewModel class to affect the view once this function is called
     * Helps with two way data binding
     */
    fun notifyChange() {
        mCallbacks.notifyChange(this, BR._all)
    }
}
