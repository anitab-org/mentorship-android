package org.systers.mentorship.view.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_add_task.view.*
import kotlinx.android.synthetic.main.list_filter_item.view.*
import org.systers.mentorship.R
import org.systers.mentorship.view.fragments.MembersFragment.Companion.filtersApplied

/**
 * This is the adapter class for the Filters recyclerView.
 * @param context context of the calling activity
 * */
class FilterAdapter(
        val context: Context
):RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {

    /**
     * The list containing the value of the headers in the Filter recyclerView
     * */
    private val filterList=listOf<String>(context.getString(R.string.name),context.getString(R.string.username),context.getString(R.string.slack_username), context.getString(R.string.interests),context.getString(R.string.bio),context.getString(R.string.location),context.getString(R.string.occupation),
            context.getString(R.string.organization),context.getString(R.string.skills),context.getString(R.string.available_to_mentor),context.getString(R.string.need_mentoring))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.list_filter_item, parent,false )
        )
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {

        val itemView=holder.itemView
        val header=filterList.get(position)
        val value=filtersApplied[header]

        itemView.tvHeader.setText(header)

        /**
         * 'Available to mentor' and 'Needs mentoring' field doesn't need any value to show up the applied filters
         * Their filters will just be applied as checked or unchecked.
         * */
        if (header!=context.getString(R.string.available_to_mentor) && header!=context.getString(R.string.need_mentoring)) {
            itemView.tvValue.setText(value)
        }

        /**
         * Checking if a filter is applied
         * */
        if (value!=null){
            itemView.cbFilter.isChecked=true
        }

        itemView.setOnClickListener {
            itemView.cbFilter.isChecked=!itemView.cbFilter.isChecked

            if (itemView.cbFilter.isChecked){
                if (header==context.getString(R.string.available_to_mentor) || header==context.getString(R.string.need_mentoring)) {
                    filtersApplied[header]=context.getString(R.string.filter_applied)
                }else{
                    showDialog(itemView,header)
                }
            }else{
                filtersApplied[header]=null
                itemView.tvValue.setText(null)
            }
        }
    }

    /**
     * The function creates a dialog box through which filters can be changed
     */
    fun showDialog(itemView: View,header:String){

        val builder=AlertDialog.Builder(context)

        builder.setTitle(context.getString(R.string.title_filter_dialog,header))

        val dialogLayout=LayoutInflater.from(context).inflate(R.layout.dialog_add_task,null)
        val editText=dialogLayout.etAddTask
        builder.setView(dialogLayout)

        builder.setPositiveButton(context.getString(R.string.btn_filter_dialog_positive)){ dialog: DialogInterface?, _: Int ->
            dialog?.dismiss()

            val value=editText.text.toString()

            itemView.tvValue.text=value
            filtersApplied[header]=value
        }

        builder.setNegativeButton(context.getString(R.string.btn_filter_dialog_negative)){dialog: DialogInterface?, _: Int ->
            itemView.cbFilter.isChecked=false
            dialog?.dismiss()
        }

        builder.show()
    }

    override fun getItemCount(): Int = filterList.size

    class FilterViewHolder (itemView: View):RecyclerView.ViewHolder(itemView)
}