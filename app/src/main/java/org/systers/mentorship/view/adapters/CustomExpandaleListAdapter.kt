package org.systers.mentorship.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.CheckBox
import android.widget.TextView
import org.systers.mentorship.R
import org.systers.mentorship.models.DashBoardResponse
import org.systers.mentorship.models.RelationState
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.models.Task
import org.systers.mentorship.utils.DATE_FORMAT
import org.systers.mentorship.utils.convertUnixTimestampIntoStr

class CustomExpandaleListAdapter(val dashBoardResponse: DashBoardResponse, val context: Context?) : BaseExpandableListAdapter() {

    private val mentor = dashBoardResponse.asMentor
    private val mentee = dashBoardResponse.asMentee
    private val pendingList = mentee.received.pending + mentee.sent.pending + mentor.received.pending + mentor.sent.pending
    private val acceptedList = mentee.received.accepted + mentee.sent.accepted + mentor.received.accepted + mentor.sent.accepted
    private val rejectedList = mentee.received.rejected + mentee.sent.rejected + mentor.received.rejected + mentor.sent.rejected
    private val completedList = mentee.received.completed + mentee.sent.completed + mentor.received.completed + mentor.sent.completed
    private val cancelledList = mentee.received.cancelled + mentee.sent.cancelled + mentor.received.cancelled + mentor.sent.cancelled
    private val tasksDone = dashBoardResponse.tasksDone
    private val taskTodo = dashBoardResponse.tasksTodo
    private val list by lazy { listOf(pendingList, acceptedList, rejectedList, completedList, cancelledList, taskTodo, tasksDone) }
    override fun getGroupCount(): Int {
        return list.size
    }

    override fun getChildrenCount(pos: Int): Int {
        return if (list[pos] == null) 0 else list[pos].size
    }

    override fun getGroup(pos: Int): String {
        return when (pos) {
            RelationState.PENDING.value - 1 -> "Pending"
            RelationState.ACCEPTED.value - 1 -> "Accepted"
            RelationState.REJECTED.value - 1 -> "Rejected"
            RelationState.COMPLETED.value - 1 -> "Completed"
            RelationState.CANCELLED.value - 1 -> "Cancelled"
            5 -> "Tasks Done"
            else -> "Tasks Todo"
        }
    }

    override fun getChild(listPos: Int, expandedlistPos: Int): Any {
        return list[listPos][expandedlistPos]
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(position: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val title = getGroup(position)
        if (convertView == null) {
            val inflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null)
        }
        val tv = convertView!!.findViewById<TextView>(android.R.id.text1)
        tv.text = title
        return convertView
    }

    override fun getChildView(listPosition: Int, elPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        if (listPosition < 5) {
            val child = getChild(listPosition, elPosition) as Relationship

            if (convertView == null) {
                val layoutInflater =
                        this.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                convertView = layoutInflater.inflate(R.layout.list_requests_item, null )
            }
            with(convertView!!) {
                findViewById<TextView>(R.id.tvMentorName).text = child.mentor.userName
                findViewById<TextView>(R.id.tvMenteeName).text = child.mentee.userName
                findViewById<TextView>(R.id.tvCreationDate).text = convertLongToTime(child.creationDate)
                findViewById<TextView>(R.id.tvEndDate).text = convertLongToTime(child.endDate)
                findViewById<TextView>(R.id.tvNotes).text = child.notes
            }
            return convertView
        } else {
            val child = getChild(listPosition, elPosition) as Task
            if (convertView == null) {
                val layoutInflater =
                        this.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                convertView = layoutInflater.inflate(R.layout.task_list_item, null)
            }
            with(convertView!!) {
                val cb = findViewById<CheckBox>(R.id.cbTask)
                cb.text = child.description
                cb.isChecked = child.isDone
            }
            return convertView
        }

    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return false
    }

    private fun convertLongToTime(time: Float): String {
        return convertUnixTimestampIntoStr(time, DATE_FORMAT)
    }
}