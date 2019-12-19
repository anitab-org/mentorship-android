package org.systers.mentorship.view.activities

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_filter.*
import org.systers.mentorship.R
import org.systers.mentorship.view.adapters.FilterAdapter
import org.systers.mentorship.view.fragments.MembersFragment.Companion.filtersApplied

class FilterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        supportActionBar?.title=getString(R.string.filters)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rvFilters.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=FilterAdapter(context)
        }

        btnApplyFilters.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_filter,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_clear_filters ->{
                for (key in filtersApplied.keys){ filtersApplied[key]=null }
                rvFilters.apply {
                    adapter=FilterAdapter(context)
                }
                return true
            }
            android.R.id.home -> {
                for (key in filtersApplied.keys){ filtersApplied[key]=null }
                setResult(Activity.RESULT_CANCELED)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
