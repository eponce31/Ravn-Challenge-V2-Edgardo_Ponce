package com.challenge.kotlin.ravn.activity.person_list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.challenge.kotlin.ravn.GetAllPersonsQuery
import com.challenge.kotlin.ravn.MainApplication
import com.challenge.kotlin.ravn.R
import com.challenge.kotlin.ravn.activity.person.PersonDetailsActivity
import com.challenge.kotlin.ravn.activity.util.Navigator

class PersonListActivity : AppCompatActivity(),
    Navigator {
    private var app: MainApplication? = null
    var content: ViewGroup? = null
    var progressBar: ProgressBar? = null
    var reciclerViewAdapter: PersonListReciclerViewAdapter? = null
    private val TAG = PersonListActivity::class.java.simpleName
    private var FEED_SIZE = 5
    var uiHandler = Handler(Looper.getMainLooper())
    var dataApolloCall: ApolloCall<GetAllPersonsQuery.Data>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_list)
        setTitle(R.string.str_people_star_wars)
        app = application as MainApplication
        content = findViewById<View>(R.id.content_holder) as ViewGroup
        progressBar =
            findViewById<View>(R.id.loading_bar) as ProgressBar
        val recyclerView =
            findViewById<View>(R.id.rv_person_list) as RecyclerView
        reciclerViewAdapter =
            PersonListReciclerViewAdapter(
                this
            )
        recyclerView.adapter = reciclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.divider)!!)
        recyclerView.addItemDecoration(decorator)

        /* todo toolbar https://github.com/g2obregon/ToolbarCenterTitleSample*/
        setupToolbar()

        fetchAllPersons()
    }


    private fun setupToolbar() {
        /*
        val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        val mTitle = toolbar.findViewById(R.id.toolbar_title) as TextView
        setSupportActionBar(toolbar)
        mTitle.setText(toolbar.getTitle())
        supportActionBar!!.setDisplayShowTitleEnabled(false)

         */
    }

    private fun fetchAllPersons() {
        app!!.apolloClient()
            ?.query(
                GetAllPersonsQuery()
            )
            ?.enqueue(object : ApolloCall.Callback<GetAllPersonsQuery.Data>() {
                override fun onResponse(response: Response<GetAllPersonsQuery.Data>) {
                    runOnUiThread {
                        reciclerViewAdapter!!.setList(response.data()!!.allPersons())
                        progressBar!!.visibility = View.GONE
                        content!!.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(e: ApolloException) {
                    Log.e(TAG, e.message, e)
                }
            })
    }



    override fun startActivity(person_id: String) {
        val intent =
            PersonDetailsActivity.newIntent(
                this,
                person_id
            )
        startActivity(intent)
    }
}