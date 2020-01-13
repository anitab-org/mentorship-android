package org.systers.mentorship.view.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.utils.PreferenceManager

/**
 * The fragment is responsible for showing notifications
 */
class NotificationFragment : BaseFragment() {
    var clearNotifications = false
    private val runnable = Runnable { updateJob() }
    private val preferenceManager: PreferenceManager = PreferenceManager()
    lateinit var listViewAdapter: ArrayAdapter<String>
    private lateinit var listView: ListView

    companion object {
        /**
         * Creates an instance of [RequestsFragment]
         */
        fun newInstance(): NotificationFragment{
            return NotificationFragment()
        }
        val TAG = NotificationFragment::class.java.simpleName
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_notification_list

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_notification_list, container, false)
        listView = view.findViewById(R.id.notification_list_view)
        updateJob()

        val deleteButton = view.findViewById<FloatingActionButton>(R.id.delete_notification_button)
        deleteButton.setOnClickListener {
            // delete data
            preferenceManager.clearNotifications()
            clearNotifications = true //read by MainActivity
        }
        return view
    }

    private fun updateJob(){
        val handler = Handler()
        val listNotifications = preferenceManager.getNotificationList()
        listViewAdapter = ArrayAdapter(
                MentorshipApplication.getContext(),
                android.R.layout.simple_list_item_1,
                generateMessages(listNotifications)
        )
        listView.adapter = listViewAdapter
        handler.postDelayed(runnable, 5000)
    }

    private fun generateMessages(notificationList: List<Relationship>):List<String>{
        val messagesList = mutableListOf<String>()
        for (relationship in notificationList){
            when(relationship.state){
                1 -> messagesList.add(
                        context!!.getString(R.string.notification_new_request,
                                findSender(relationship).name,
                                findSenderRole(relationship)))
                2 -> messagesList.add(
                        context!!.getString(R.string.notification_accepted,
                                findSender(relationship).name))
                3 -> messagesList.add(
                        context!!.getString(R.string.notification_rejected,
                                findSender(relationship).name))
                4 -> messagesList.add(
                        context!!.getString(R.string.notification_cancelled,
                                findSender(relationship).name,
                                findSenderRole(relationship)))
                5 -> messagesList.add(
                        context!!.getString(R.string.notification_completed,
                                findSender(relationship).name,
                                findSenderRole(relationship)))
            }
        }
        return messagesList
    }
    private fun findSender(relationship: Relationship): Relationship.RelationUserResponse{
        // helper function to find sender
        if(relationship.actionUserId == relationship.mentee.id){
            return relationship.mentee
        }
        return relationship.mentor
    }
    private fun findSenderRole(relationship: Relationship): String{
        // helper function to find sender
        if(relationship.actionUserId == relationship.mentee.id){
            return context!!.getString(R.string.mentee)
        }
        return context!!.getString(R.string.mentor)
    }
}
