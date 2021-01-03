package org.systers.mentorship.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentRequestPagerBinding
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.view.activities.RequestDetailActivity
import org.systers.mentorship.view.adapters.RequestsAdapter

/**
 * This fragment is instantiated per each tab from the RequestsFragment ViewPager
 */
class RequestPagerFragment: BaseFragment() {

    private var _requestPagerBinding: FragmentRequestPagerBinding? = null
    private val requestPagerBinding get() = _requestPagerBinding!!

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

        val deletedRequests = mutableSetOf<Int>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _requestPagerBinding = FragmentRequestPagerBinding.inflate(inflater,container,false)
        return requestPagerBinding.root
    }

    private lateinit var requestsList: MutableList<Relationship>
    private lateinit var emptyListText: String

    override fun getLayoutResourceId() = R.layout.fragment_request_pager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            requestsList = it.getParcelableArrayList(Constants.REQUEST_LIST)
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
        val id = data?.getStringExtra(Constants.REQUEST_ID)
        if (id != null) {
            deletedRequests.add(id.toInt())
        }

        setView()
    }
    
    private fun setView() {
        val filtered = requestsList.filter {
            val id = it.id
            !deletedRequests.contains(id)
        }

        requestPagerBinding.apply {
            if (filtered.isEmpty()) {
                tvEmptyList.text = emptyListText
                rvRequestsList.visibility = View.GONE
            } else {
                rvRequestsList.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = RequestsAdapter(filtered, openRequestDetail)
                }
                tvEmptyList.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _requestPagerBinding = null
    }
}
