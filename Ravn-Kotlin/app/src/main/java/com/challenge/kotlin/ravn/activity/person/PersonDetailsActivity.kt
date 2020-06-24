package com.challenge.kotlin.ravn.activity.person

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.challenge.kotlin.ravn.GraphQLUtil
import com.challenge.kotlin.ravn.MainApplication
import com.challenge.kotlin.ravn.PersonDetailsQuery
import com.challenge.kotlin.ravn.R
import com.challenge.kotlin.ravn.activity.util.StringUtil

class PersonDetailsActivity : AppCompatActivity() {
    private var application: MainApplication? = null
    var content: ViewGroup? = null
    var progressBar: ProgressBar? = null
    var tv_eye_color: TextView? = null
    var tv_hair_color: TextView? = null
    var tv_skin_color: TextView? = null
    var tv_birth_year: TextView? = null
    var ll_vehicles: LinearLayout? = null
    private var personId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)
        personId = intent.getStringExtra(ARG_PERSON_ID)
        application = getApplication() as MainApplication
        content = findViewById<View>(R.id.content_holder) as ViewGroup
        progressBar =
            findViewById<View>(R.id.loading_bar) as ProgressBar
        tv_eye_color = findViewById<View>(R.id.tv_eye_color) as TextView
        tv_hair_color = findViewById<View>(R.id.tv_hair_color) as TextView
        tv_skin_color = findViewById<View>(R.id.tv_skin_color) as TextView
        tv_birth_year = findViewById<View>(R.id.tv_birth_year) as TextView
        ll_vehicles = findViewById<View>(R.id.ll_vehicles) as LinearLayout
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        fetchPersonDetailsDetailsByID(personId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setEntryData(data: PersonDetailsQuery.Data?) {
        content!!.visibility = View.VISIBLE
        progressBar!!.visibility = View.GONE
        val entry = data!!.Person()
        if (entry != null) {
            title = entry.name()
            tv_eye_color!!.text = StringUtil.formatGraphQLQueryText(
                entry.eyeColor().toString()
            )
            tv_skin_color!!.text = StringUtil.formatGraphQLQueryText(
                entry.skinColor().toString()
            )
            tv_hair_color!!.text = StringUtil.formatGraphQLQueryText(
                entry.hairColor().toString()
            )
            tv_birth_year!!.text = entry.birthYear()

            val vehicles: List<String> = GraphQLUtil.getVehices(entry.vehicles()) as List<String>;


            for (index in vehicles.indices) {

                // creating TextView programmatically
                val tv_dynamic = TextView(ContextThemeWrapper(this, R.style.TextStyleItem), null, 0)
                tv_dynamic.text = vehicles.get(index)

                // creating View divider programmatically
                // val vw_divider = View( ContextThemeWrapper(this, R.style.DividerStyle), null, 0)

                // add TextView to LinearLayout
                ll_vehicles!!.addView(tv_dynamic)
                //ll_vehicles!!.addView(vw_divider)
            }


        }
    }

    private fun fetchPersonDetailsDetailsByID(personID: String?) { //PersonDetailsQuery personDetailsQuery = PersonDetailsQuery.builder().personId(personID).build();
        application!!.apolloClient()
            ?.query(
                PersonDetailsQuery(personID!!)
            )
            ?.enqueue(object : ApolloCall.Callback<PersonDetailsQuery.Data?>() {
                override fun onResponse(response: Response<PersonDetailsQuery.Data?>) {
                    runOnUiThread {
                        setEntryData(response.data())
                        progressBar!!.visibility = View.GONE
                        content!!.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(e: ApolloException) {
                    Log.e(TAG, e.message, e)
                }
            })
    }

    companion object {
        private val TAG = PersonDetailsActivity::class.java.simpleName
        private const val ARG_PERSON_ID = "arg_person_id"
        fun newIntent(context: Context?, personId: String?): Intent {
            val intent = Intent(context, PersonDetailsActivity::class.java)
            intent.putExtra(ARG_PERSON_ID, personId)
            return intent
        }
    }
}