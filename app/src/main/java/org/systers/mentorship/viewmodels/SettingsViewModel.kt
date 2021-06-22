package org.systers.mentorship.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.db.AppDb

/**
 * This class represents the [ViewModel] used for SettingsActivity
 */
class SettingsViewModel : ViewModel() {
    /**
     * Deletes relationships from the database
     */

    fun deleteRelationshipFromDb(context: Context, id: Int) = viewModelScope.launch {
        val relationshipDao = AppDb(context).getRelationshipDao()
        relationshipDao.deleteRelatioship(id)
    }
}
