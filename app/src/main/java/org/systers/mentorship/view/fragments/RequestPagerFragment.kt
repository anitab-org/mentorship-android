package org.systers.mentorship.view.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_request_pager.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.view.activities.RequestDetailActivity
import org.systers.mentorship.view.adapters.RequestsAdapter

/**
 * This fragment is instantiated per each tab from the RequestsFragment ViewPager
 */
class RequestPagerFragment: BaseFragment() {

    companion object {
        /**
         * Creates an instance of [RequestPagerFragment]
         */
        fun newInstance(requestsList: List<Relationship>, emptyListText: String) : BaseFragment {

            val args = Bundle()
            args.putParcelableArrayList(Constants.REQUEST_LIST, ArrayList(requestsList))
            args.putString(Constants.REQUEST_EMPTY_LIST_TEXT, emptyListText)

            val frag = RequestPagerFragment()
            frag.arguments = args

            return frag
        }
    }

    private lateinit var requestsList: MutableList<Relationship>
    private lateinit var emptyListText: String

    private lateinit var preferenceManager:SharedPreferences
    private var deletedRequests: MutableSet<String>? = mutableSetOf()

    override fun getLayoutResourceId() = R.layout.fragment_request_pager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /**
         * Using shared preferences to store the id(s) of deleted requests for reference
         * */
        preferenceManager= context!!.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE)
        preferenceManager?.edit()?.putStringSet(Constants.SHARED_PREFERENCE_KEY,deletedRequests)?.commit()
        deletedRequests=preferenceManager.getStringSet(Constants.SHARED_PREFERENCE_KEY,null)

        arguments?.let {
            requestsList = it.getParcelableArrayList(Constants.REQUEST_LIST)!!
            emptyListText = it.getString(Constants.REQUEST_EMPTY_LIST_TEXT)
        }
        setView()
    }

    private val openRequestDetail: (Relationship) -> Unit =
            { requestDetail ->
                val intent = Intent(activity, RequestDetailActivity::class.java)
                intent.putExtra(Constants.RELATIONSHIP_EXTRA, requestDetail)
                startActivityForResult(intent, Constants.DELETE_REQUEST_RESULT_ID)
            }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val id=data?.getStringExtra(Constants.REQUEST_ID)

        /**
         * Adding newly deleted requests to SharedPreference
         * */
        deletedRequests!!.add(id.toString())
        preferenceManager?.edit()?.putStringSet(Constants.SHARED_PREFERENCE_KEY,deletedRequests)?.commit()

        refreshList(deletedRequests!!)
        setView()
    }

    private fun setView() {
        /**
         * Checking for any deleted requests
         * */
        if (deletedRequests!!.size>0){
            refreshList(deletedRequests!!)
        }

        if (requestsList.isEmpty()) {
            tvEmptyList.text = emptyListText
            tvEmptyList.visibility = View.VISIBLE
            rvRequestsList.visibility = View.GONE
        } else {
            rvRequestsList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = RequestsAdapter(requestsList, openRequestDetail)
            }
            RequestPagerFragment.newInstance(requestsList,"")
            tvEmptyList.visibility = View.GONE
        }
    }

    /**
     * This method removes the deleted requests from the main list of 'requestsList'
     * by comparing the requests in the two sets with their id,
     * and then the setView() method updates the current page with updated list.
     * */
    private fun refreshList(id: MutableSet<String>) {

        val iter = requestsList.iterator()
        while (iter.hasNext()) {
            val reqId = iter.next().id
            if(reqId.toString() in id) {
                iter.remove()
            }
        }
    }
}
